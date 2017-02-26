package com.dev.bins.shop.net;

import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.Category;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.Recommend;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bin on 24/02/2017.
 */
public interface Api {
    String LOCAL_URL = "http://192.168.1.118:8080/";


    /**
     * 滚动条
     * @return
     */
    @GET("Banner")
    Observable<List<Banner>> getBanner();

    /**
     * 推荐
     * @return
     */
    @GET("recommend")
    Observable<List<Recommend>> getRecommend();

    /**
     * 热卖
     * @param page 当前页
     * @param pageSize 每页大小
     * @return
     */
    @GET("hot")
    Observable<Goods> getGoodsWithPage(@Query("curPage") int page, @Query("pageSize") int pageSize);

    /**
     * 分类列表
     * @return
     */
    @GET("category/list")
    Observable<List<Category>> getCategory();

    /**
     * 分类
     * @param cid 分类ID
     * @param curPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @GET("category")
    Observable<Goods> getCategoryData(@Query("cid") int cid, @Query("curPage") int curPage, @Query("pageSize") int pageSize);
}
