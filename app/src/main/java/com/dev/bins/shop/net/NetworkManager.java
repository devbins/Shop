package com.dev.bins.shop.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bin on 24/02/2017.
 */

public class NetworkManager {


    private static NetworkManager mNetworkManager = new NetworkManager();
    private final Retrofit mRetrofit;

    private NetworkManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static NetworkManager getInstance() {
        return mNetworkManager;
    }







}
