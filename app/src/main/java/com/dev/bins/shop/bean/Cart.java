package com.dev.bins.shop.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by bin on 25/02/2017.
 */


public class Cart extends DataSupport {



    @Column(unique = true,nullable = false)
    private long id;

    private int count;
    private boolean isChecked;
    private GoodsItem goodsItem;

    public Cart() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart(GoodsItem goodsItem, int count, boolean isChecked) {
        this.goodsItem = goodsItem;
        this.count = count;
        this.isChecked = isChecked;
    }

    public GoodsItem getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(GoodsItem goodsItem) {
        this.goodsItem = goodsItem;
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

    @Override
    public String toString() {
        return "Cart{" +
                "goodsItem=" + goodsItem.toString() +
                ", id=" + id +
                ", count=" + count +
                ", isChecked=" + isChecked +
                '}';
    }
}
