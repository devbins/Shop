package com.dev.bins.shop.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by bin on 25/02/2017.
 */

public class District implements IPickerViewData{

    String name;
    String code;

    public District() {
    }

    public District(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
