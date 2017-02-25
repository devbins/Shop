package com.dev.bins.shop.bean;

/**
 * Created by bin on 25/02/2017.
 */

public class Cart extends GoodsItem {


    private int count;
    private boolean isChecked;

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
}
