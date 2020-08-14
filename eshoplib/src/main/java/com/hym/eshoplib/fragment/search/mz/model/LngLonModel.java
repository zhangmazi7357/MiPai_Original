package com.hym.eshoplib.fragment.search.mz.model;

import java.io.Serializable;

/**
 * 经纬度
 */
public class LngLonModel implements Serializable {


    private String lon;
    private String lat;
    private String address;

    public LngLonModel(String lon, String lat, String address) {
        this.lon = lon;
        this.lat = lat;
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
