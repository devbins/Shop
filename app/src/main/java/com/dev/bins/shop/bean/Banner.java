package com.dev.bins.shop.bean;

import com.dev.bins.shop.net.Api;

/**
 * Created by bin on 25/02/2017.
 */

public class Banner {


    private String name;
    private String imgUrl;
//    private String type;
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return Api.BASE_URL+"image?name="+imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
