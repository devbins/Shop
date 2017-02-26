package com.dev.bins.shop.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/02/2017.
 */

public class City {

    String name;
    ArrayList<District> districts;

    public City() {
        this.name = name;
    }

    public City(String name, ArrayList<District> districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
