package runjoy.data;

import com.amap.api.maps.model.LatLng;

import java.io.Serializable;

import runjoy.tool.LocationUtils;

/**
 * Created by JiachenWang on 2017/4/3.
 */

public class TargetPoint implements Serializable {

    private int id;
    private String describe;
    private double latitude;
    private double longitude;

    public TargetPoint(LatLng latLng, int id, String describe) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        this.id = id;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLng(){
        return new LatLng(this.getLatitude(),this.getLongitude());
    }
    @Override
    public String toString() {
        return "TargetPoint{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
