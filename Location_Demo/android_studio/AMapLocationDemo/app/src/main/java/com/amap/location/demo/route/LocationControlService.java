package com.amap.location.demo.route;

import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.model.LatLng;

/**
 * Created by JiachenWang on 2017/2/17.
 */

public interface LocationControlService {

    /**
     * 初始化定位
     */
    void initLocation();

    /**
     * 是否在抖动范围内（即可以忽视的位置更改）
     *
     * @param lastLoc 上一个有效位置点
     * @param newLoc  定位到的新位置（不确定是否有效）
     * @return
     */
    boolean inJitter(LatLng lastLoc, LatLng newLoc);

    /**
     * 默认的定位参数
     */
    AMapLocationClientOption getDefaultOption();

    /**
     * 开始定位
     */
    void startLocation();

    /**
     * 停止定位
     */
    void stopLocation();

    /**
     * 销毁定位
     */
    void destroyLocation();
}
