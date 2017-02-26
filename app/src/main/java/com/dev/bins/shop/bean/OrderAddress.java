package com.dev.bins.shop.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by bin on 26/02/2017.
 */

public class OrderAddress extends DataSupport {
    private long id;
    private String name;
    private String phone;
    private String add;
    private String address;

    public  OrderAddress(){

    }

    public OrderAddress(String name, String phone, String add, String address) {
        this.name = name;
        this.phone = phone;
        this.add = add;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
