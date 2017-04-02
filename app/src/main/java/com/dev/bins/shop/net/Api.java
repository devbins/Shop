package com.dev.bins.shop.net;

import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.Category;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.OrderAddress;
import com.dev.bins.shop.bean.Recommend;
import com.dev.bins.shop.bean.ResponseMsg;
import com.dev.bins.shop.bean.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bin on 24/02/2017.
 */
public interface Api {
    String BASE_URL = "http://192.168.31.177:8080/";

    /**
     * @param phone 电话号码
     * @param pwd   密码
     * @return 返回注册结果
     */
    @FormUrlEncoded
    @POST("login")
    Observable<ResponseMsg<User>> login(@Field("phone") String phone, @Field("pwd") String pwd);

    /**
     * @param phone 电话号码
     * @param pwd   密码
     * @return
     */
    @FormUrlEncoded
    @POST("reg")
    Observable<ResponseMsg<User>> register(@Field("phone") String phone, @Field("pwd") String pwd);

    /**
     * 滚动条
     *
     * @return
     */
    @GET("banner")
    Observable<ResponseMsg<List<Banner>>> getBanner();

    /**
     * 推荐
     *
     * @return
     */
    @GET("recommend")
    Observable<ResponseMsg<List<Recommend>>> getRecommend();

    /**
     * 热卖
     *
     * @param page     当前页
     * @param pageSize 每页大小
     * @return
     */
    @GET("find")
    Observable<ResponseMsg<Goods>> getGoodsWithPage(@Query("curPage") int page, @Query("pageSize") int pageSize);

    /**
     * 分类列表
     *
     * @return
     */
    @GET("category/list")
    Observable<ResponseMsg<List<Category>>> getCategory();

    /**
     * 分类
     *
     * @param cid      分类ID
     * @param curPage  当前页
     * @param pageSize 每页大小
     * @return
     */
    @GET("category")
    Observable<ResponseMsg<Goods>> getCategoryData(@Query("cid") int cid, @Query("curPage") int curPage, @Query("pageSize") int pageSize);

    /**
     * 获取用户收货地址
     * @param cid 用户id
     * @return
     */
    @GET("address/{cid}")
    Observable<ResponseMsg<List<OrderAddress>>> getAddress(@Path("cid") int cid);
}
