package runjoy.data;

import com.amap.api.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by JiachenWang on 2017/4/3.
 */

public class TargetPoint implements Serializable {

    private int id;
    private String describe;
    private LatLng latLng;

    public TargetPoint(LatLng latLng, int id, String describe) {
        this.latLng = latLng;
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

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        return "TargetPoint{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                ", latLng=" + latLng +
                '}';
    }
}
