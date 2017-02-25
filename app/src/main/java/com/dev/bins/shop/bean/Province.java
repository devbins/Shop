package com.dev.bins.shop.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by bin on 25/02/2017.
 */

public class Province implements IPickerViewData{


    String name;
    List<City> cities;

    public Province() {
    }

    public Province(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
