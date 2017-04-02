package runjoy.running.location;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.LocationSource;

/**
 * Created by JiachenWang on 2017/2/4.
 */

public interface LocationListener {

    /**
     * 位置信息改变，设备移动
     *
     * @param amapLocation 改变的位置信息
     */
    void onLocationMove(AMapLocation amapLocation);

    /**
     * LocationSource
     *
     * @param listener
     */
    void locationSource(LocationSource.OnLocationChangedListener listener);
}
