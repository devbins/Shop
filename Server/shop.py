# -*- coding=utf-8 -*-
import web
import json
import sqlite3 as database
import hashlib

#errorcode
#0 表示无错误
#1 表示账号不存在
#2 表示密码错误
#3 

urls = (
    '/','index',
    '/img','img',
    '/seeother','seeother',
    '/banner','Banner',
    '/recommend','Recommend',
    '/find','Find',
    '/category/list','Clist',
    '/category','Category',
    '/login','Login',
    '/reg','Reg',
    '/image','Image',
    '/address','Address'
)
db = web.database(dbn='sqlite', db='shop.db',)
class index:
    def GET(self):
        return "welcome"
    def POST(self):
        return 'welcome'


class Reg:
    def POST(self):
        in_data = web.input()
        phone = in_data['phone']
        pwd = in_data['pwd']
        #sql_instruct = "insert into user ('phone','pwd') values('%s','%s');"%(phone,pwd)
        #res = sql().cursor.execute(sql_instruct)
        phones = db.select('user',where="phone=$phone",vars={'phone':phone})
        db.insert('user',phone=phone,pwd=pwd)
        return json.dumps({'errorcode':0,'errormsg':'','data':{'user':{'phone':phone,'avatar':''}}})
        

class Login:
    def POST(self):
        in_data = web.input()
        oldpwd = in_data['phone']
        #data = sql().cursor.execute("select * from user where phone=%s"%(in_data['phone']))
        data = db.select('user',where="phone=$phone",vars={'phone':in_data['phone']})[0]
        pwd = data.pwd
        #pwd = data[2]
        errorcode = cmp(pwd,oldpwd)
        return json.dumps({'errorcode':errorcode,'errormsg':'','data':{'phone':in_data['phone'],'avatar':data.avatar}})
       

class Address:
    def GET(self):
        params = web.input()
        address = db.select('address',where="cid=$cid",vars={'cid':params['cid']})
        return json.dumps({'errorcode':0,'errormsg':'','data':{address}})

    
class img:
    def GET(self):
        var = web.input()
        b = web.ctx.env
        return "{a}{b}".format(a=var,b=b)
class seeother:
    def GET(self):
        return web.seeother('/')

class Banner:
    def GET(self):
        #banner_data = sql().cursor.execute('select * from banner')
        banner_data = db.select('banner')
        data = []
        for row in banner_data:
            dict = {'id':row.id,'name':row.name,'imgUrl':row.imgUrl}
            data.append(dict)
        return json.dumps({'errorcode':0,'errormsg':'','data':data})

class Recommend:
    def GET(self):
        data = []
        #recs = sql().cursor.execute('select * from recommend')
        recs = db.select('recommend')
        for rec in recs:
            recommend = {'id':rec.id,'name':rec.name,'promote1':{},'promote2':{},'promote3':{}}
            #pros = sql().cursor.execute('select * from promote where recommendid=%s'%(rec[0]))
            pros = db.select('promote',where="recommendid=$id",vars={'id':rec.id})
            i = 1
            for pro in pros:
                promote = {'id':pro.id,'title':pro.title,'imgUrl':pro.imgUrl}
                recommend['promote%s'%(i)]=promote
                i = i+1
            data.append(recommend)
        return json.dumps({'errorcode':0,'errormsg':'','data':data})

class Find:
    def GET(self):
        get = web.input()
        data = {'currentPage':get['curPage'],'pageSize':get['pageSize']}
        start = (int(get.curPage)-1)*int(get.pageSize)
        pageSize = int(get.pageSize)
        #sqls ='select * from goods where id>=%s limit %s'%(stat,pageSize) 
        #goods = sql().cursor.execute(sqls)
        #data['totalPage']=count(goods)
        #data['totalCount']=count(goods)/int(get['pageSize'])
        goods = db.select('goods',where="id>=$id",limit="$pageSize",vars={'id':start,'pageSize':pageSize})
        data['goods']=[]
        i = 0;
        for good in goods:
            i = i+1
            data['goods'].append({'id':good.id,'name':good.name,'imgUrl':good.imgUrl,'desc':good.desc,
                                  'price':good.price})            
        data['totalCount']=i
        data['totalPage']=i/pageSize+1
        return json.dumps({'errorcode':0,'errormsg':'','data':data})

class Category:
    def GET(self):
        get = web.input()
        cid = int(get.cid)
        pageSize = int(get.pageSize)
        start = (int(get.curPage)-1)*int(pageSize)
        data = {'currentPage':get['curPage'],'pageSize':pageSize}
        #sqls = 'select * from goods where cid=%s limit %s'%(cid,pageSize)
        #goods = sql().cursor.execute(sqls)
        goods = db.select('goods',where="cid=$cid",limit="$pageSize",vars={'cid':cid,'pageSize':pageSize})
        data['goods']=[]
        i = 0;
        for good in goods:
            i = i+1
            data['goods'].append({'id':good.id,'name':good.name,'imgUrl':good.imgUrl,'desc':good.desc,
                                  'price':good.price})            
        data['totalCount']=i
        data['totalPage']=i/pageSize
        return json.dumps({'errorcode':0,'errormsg':'','data':data})

class Image:
    def GET(self):
        get = web.input();
        name = get.name
        return open('./img/%s'%(name),'r')


class Clist:
    def GET(self):
        data = []
        #clist = sql().cursor.execute('select * from clist')
        clist = db.select('clist')
        for category in clist:
            data.append({'id':category.id,'name':category.name})
        return json.dumps({'errorcode':0,'errormsg':'','data':data})



class sql:
    def __init__(self):
        self.conn = database.connect('shop.db')
        self.cursor = self.conn.cursor()
        #self.cursor.execute("""create table banner
        #    (id integer primary key,name varchar(20),imgUrl varchar(128))
        #   """)


if __name__ == "__main__":
    app = web.application(urls,globals())
    app.run()


