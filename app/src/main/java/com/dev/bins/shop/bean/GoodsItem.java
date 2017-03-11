package com.dev.bins.shop.bean;

import com.dev.bins.shop.net.Api;

import org.litepal.crud.DataSupport;

/**
 * Created by bin on 25/02/2017.
 */

public class GoodsItem extends DataSupport {


    private long id;
    private String name;
    private String imgUrl;
    private String desc;
    private float price;
    private int count;
    private boolean isChecked;

    public GoodsItem() {
    }

    public GoodsItem(GoodsItem goodsItem) {
        this(goodsItem.getId(), goodsItem.getName(), goodsItem.getImgUrl(), goodsItem.getDesc(), goodsItem.getPrice());
    }

    public GoodsItem(long id, String name, String imgUrl, String desc, float price) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.price = price;
    }

    public GoodsItem(String name, String imgUrl, String desc, float price) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodsItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", isChecked=" + isChecked +
                '}';
    }
}
