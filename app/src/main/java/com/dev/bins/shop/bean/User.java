package com.dev.bins.shop.bean;

import com.dev.bins.shop.net.Api;

/**
 * Created by bin on 12/03/2017.
 */

public class User extends BaseBean{

    private String phone;
    private String avatar;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return Api.BASE_URL+"imgae/"+avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
