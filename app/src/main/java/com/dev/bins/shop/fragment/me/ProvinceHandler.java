package com.dev.bins.shop.fragment.me;

import com.dev.bins.shop.bean.City;
import com.dev.bins.shop.bean.District;
import com.dev.bins.shop.bean.Province;

import org.xml.sax.Attributes;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/02/2017.
 */
public class ProvinceHandler extends DefaultHandler {

    private ArrayList<Province> provinceList = new ArrayList<>();

    Province province;
    City city;
    District district;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("province".equals(qName)){
            province = new Province();
            province.setName(attributes.getValue(0));
            province.setCities(new ArrayList<City>());
        }else if ("city".equals(qName)){
            city = new City();
            city.setName(attributes.getValue(0));
            city.setDistricts(new ArrayList<District>());
        }else if ("district".equals(qName)){
            district = new District();
            district.setName(attributes.getValue(0));
            district.setCode(attributes.getValue(1));
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if ("province".equals(qName)){
           city.getDistricts().add(district);
        }else if ("city".equals(qName)){
           province.getCities().add(city);
        }else if ("district".equals(qName)){
           provinceList.add(province);
        }
    }

    public ArrayList<Province> getProvinceList() {
        return provinceList;
    }
}
