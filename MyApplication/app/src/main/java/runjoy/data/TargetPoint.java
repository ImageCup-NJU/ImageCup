package runjoy.data;

import com.amap.api.maps.model.LatLng;

/**
 * Created by JiachenWang on 2017/4/3.
 */

public class TargetPoint {

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
}
