package com.amap.location.demo.route;

import com.amap.api.location.AMapLocation;

/**
 * Created by JiachenWang on 2017/2/4.
 */

public interface LocationListener {

    /**
     * 位置信息改变，设备移动
     * @param loc 改变的位置信息
     */
    public void moveLocation(AMapLocation loc);
}
