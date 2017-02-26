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
    String BASE_URL = "http://112.124.22.238:8081/course_api/";
    String LOCAL_URL = "http://192.168.10.118:8080/";


//    @GET("banner/query")
//    Observable<List<Banner>> getBanner(@Query("type") long type);
    @GET("Banner")
    Observable<List<Banner>> getBanner();

//    @GET("campaign/recommend")
//    Observable<List<Recommend>> getRecommend();
    @GET("recommend")
    Observable<List<Recommend>> getRecommend();

    //curPage=1&pageSize=10
//    @GET("wares/hot")
    @GET("hot")
    Observable<Goods> getGoodsWithPage(@Query("curPage") int page, @Query("pageSize") int pageSize);

    @GET("category/list")
    Observable<List<Category>> getCategory();

    //categoryId="+categoryId+"&curPage="+currPage+"&pageSize="+pageSize;
    @GET("category")
    Observable<Goods> getCategoryData(@Query("cid") int cid, @Query("curPage") int curPage, @Query("pageSize") int pageSize);
}
