package com.dev.bins.shop.net;

import com.dev.bins.shop.bean.Address;
import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.BaseBean;
import com.dev.bins.shop.bean.Category;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.Recommend;
import com.dev.bins.shop.bean.ResponseMsg;
import com.dev.bins.shop.bean.User;

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


    public Subscription login(Subscriber<ResponseMsg<User>> subscriber, String phone, String pwd) {
        return getData(mApi.login(phone, pwd), subscriber);
    }

    public Subscription reg(Subscriber<ResponseMsg<User>> subscriber, String phone, String pwd){
        return getData(mApi.register(phone,pwd),subscriber);
    }

    public Subscription getBanner(Subscriber<ResponseMsg<List<Banner>>> subscriber) {
        return getData(mApi.getBanner(), subscriber);
    }

    public Subscription getRecommend(Subscriber<ResponseMsg<List<Recommend>>> subscriber) {
        return getData(mApi.getRecommend(), subscriber);
    }

    public Subscription getGoods(Subscriber<ResponseMsg<Goods>> subscriber, int page, int pageSize) {
        return getData(mApi.getGoodsWithPage(page, pageSize), subscriber);
    }

    public Subscription getCategory(Subscriber<ResponseMsg<List<Category>>> subscriber) {
        return getData(mApi.getCategory(), subscriber);
    }

    public Subscription getCategoryData(Subscriber<ResponseMsg<Goods>> subscriber, int cid, int curPage, int pageSize) {
        return getData(mApi.getCategoryData(cid, curPage, pageSize), subscriber);
    }


    public Subscription getAddress(Subscriber<ResponseMsg<List<Address>>> subscriber,int cid){
        return getData(mApi.getAddress(cid),subscriber);
    }


    public <T> Subscription getData(Observable<T> observable, Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
