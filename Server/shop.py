# -*- coding=utf-8 -*-
import web
import json
import sqlite3 as db
import hashlib
urls = (
    '/','index',
    '/img','img',
    '/seeother','seeother',
    '/Banner','Banner',
    '/recommend','Recommend',
    '/hot','Hot',
    '/category/list','Clist',
    '/category','Category',
    '/login','Login',
    '/reg','Reg'
)
DB = web.database(dbn='sqlite', db='shop', user='', pw='')
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
        sql_instruct = "insert into user ('phone','pwd') values('%s','%s');"%(phone,pwd)
        res = sql().cursor.execute(sql_instruct)
        return json.dumps({'errorcode':0})
        
    
class Login:
    def POST(self):
        in_data = web.input()
        m = hashlib.md5()
        m.update(in_data['pwd'])
        pwd = m.hexdigest()
        data = sql().cursor.execute("select * from user where phone=%s"%(in_data['phone']))
        m.update(data['pwd'])
        oldpwd = m.hexdigest()
        errorcode = cmp(pwd,oldpwd)
        return json.dumps({'errorcode':errorcode})
       
    
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
        banner_data = sql().cursor.execute('select * from banner')
        data = []
        for row in banner_data:
            dict = {'id':row[0],'name':row[1],'imgUrl':row[2]}
            data.append(dict)
        return json.dumps(data)

class Recommend:
    def GET(self):
        data = []
        recs = sql().cursor.execute('select * from recommend')
        for rec in recs:
            recommend = {'id':rec[0],'name':rec[1],'promote1':{},'promote2':{},'promote3':{}}
            pros = sql().cursor.execute('select * from promote where recommendid=%s'%(rec[0]))
            i = 1
            for pro in pros:
                promote = {'id':pro[0],'title':pro[1],'imgUrl':pro[2]}
                recommend['promote%s'%(i)]=promote
                i = i+1
            data.append(recommend)
        return json.dumps(data)

class Hot:
    def GET(self):
        get = web.input()
        data = {'currentPage':get['curPage'],'pageSize':get['pageSize']}
        stat = (int(get.curPage)-1)*int(get.pageSize)
        pageSize = int(get.pageSize)
        sqls ='select * from goods where id>=%s limit %s'%(stat,pageSize) 
        goods = sql().cursor.execute(sqls)
        #data['totalPage']=count(goods)
        #data['totalCount']=count(goods)/int(get['pageSize'])
        data['goods']=[]
        i = 0;
        for good in goods:
            i = i+1
            data['goods'].append({'id':good[0],'name':good[1],'imgUrl':good[2],'desc':good[3],'price':good[4]})            
        data['totalCount']=i
        data['totalPage']=i/pageSize+1
        return json.dumps(data)

class Category:
    def GET(self):
        get = web.input()
        cid = int(get.cid)
        pageSize = int(get.pageSize)
        start = (int(get.curPage)-1)*int(pageSize)
        data = {'currentPage':get['curPage'],'pageSize':pageSize}
        sqls = 'select * from goods where cid=%s limit %s'%(cid,pageSize)
        goods = sql().cursor.execute(sqls)
        data['goods']=[]
        i = 0;
        for good in goods:
            i = i+1
            data['goods'].append({'id':good[0],'name':good[1],'imgUrl':good[2],'desc':good[3],'price':good[4]})            
        data['totalCount']=i
        data['totalPage']=i/pageSize
        return json.dumps(data)

class Clist:
    def GET(self):
        data = []
        clist = sql().cursor.execute('select * from clist')
        for category in clist:
            data.append({'id':category[0],'name':category[1]})
        return json.dumps(data)



class sql:
    def __init__(self):
        self.conn = db.connect('shop.db')
        self.cursor = self.conn.cursor()
        #self.cursor.execute("""create table banner
        #    (id integer primary key,name varchar(20),imgUrl varchar(128))
        #   """)


if __name__ == "__main__":
    app = web.application(urls,globals())
    app.run()


