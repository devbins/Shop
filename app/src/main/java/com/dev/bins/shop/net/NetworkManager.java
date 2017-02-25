package com.dev.bins.shop.net;

import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.Recommend;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .baseUrl(Api.BASE_URL)
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
        return mApi.getBanner(1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription getRecommend(Subscriber<List<Recommend>> subscriber){
        return mApi.getCampaign()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription getGoods(Subscriber<Goods> subscriber,int page,int pageSize){
        return mApi.getGoodsWithPage(page,pageSize)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
