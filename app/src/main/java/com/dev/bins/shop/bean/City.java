package com.dev.bins.shop.bean;

import java.util.List;

/**
 * Created by bin on 25/02/2017.
 */

public class City {

    String name;
    List<District> districts;

    public City() {
        this.name = name;
    }

    public City(String name, List<District> districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
