package com.dev.bins.shop.net;

import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.Category;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.Recommend;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by bin on 24/02/2017.
 */

public class NetworkManager {


    private static NetworkManager mNetworkManager = new NetworkManager();
    private final Retrofit mRetrofit;
    private final Api mApi;

    private NetworkManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.LOCAL_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApi = mRetrofit.create(Api.class);
    }

    public static NetworkManager getInstance() {
        return mNetworkManager;
    }


    public Subscription getBanner(Subscriber<List<Banner>> subscriber) {
//        return mApi.getBanner()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        return getData(mApi.getBanner(), subscriber);
    }

    public Subscription getRecommend(Subscriber<List<Recommend>> subscriber) {
//        return mApi.getRecommend()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        return getData(mApi.getRecommend(), subscriber);
    }

    public Subscription getGoods(Subscriber<Goods> subscriber, int page, int pageSize) {
//        return mApi.getGoodsWithPage(page, pageSize)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        return getData(mApi.getGoodsWithPage(page, pageSize), subscriber);
    }

    public Subscription getCategory(Subscriber<List<Category>> subscriber) {
//        return mApi.getCategory()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        return getData(mApi.getCategory(), subscriber);
    }

    public Subscription getCategoryData(Subscriber<Goods> subscriber, int cid, int curPage, int pageSize) {
//        return mApi.getCategoryData(cid,curPage,pageSize)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        return getData(mApi.getCategoryData(cid, curPage, pageSize), subscriber);
    }

    public <T> Subscription getData(Observable<T> observable, Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
