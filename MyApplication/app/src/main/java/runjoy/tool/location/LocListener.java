package runjoy.tool.location;

import com.amap.api.location.AMapLocation;

/**
 * Created by JiachenWang on 2017/4/3.
 */

public interface LocListener {

    /**
     * 位置信息改变，设备移动
     *
     * @param amapLocation 改变的位置信息
     */
    void onLocationMove(AMapLocation amapLocation);
}
