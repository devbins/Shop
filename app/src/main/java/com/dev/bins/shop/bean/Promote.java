package com.dev.bins.shop.bean;

import com.dev.bins.shop.net.Api;

/**
 * Created by bin on 25/02/2017.
 */

public class Promote {

    private long id;
    private String title;
    private String imgUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return Api.BASE_URL+"image?name="+imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
