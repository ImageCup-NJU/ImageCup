package runjoy.tool.location;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;

import java.util.Iterator;
import java.util.List;

import runjoy.data.TargetPoint;
import runjoy.tool.LocationUtils;

/**
 * Created by JiachenWang on 2017/2/1.
 */
public class RunningLocController {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    private Context context;
    private RunningLocListener runningLocListener;
    private List<TargetPoint> targetPoints;
    private boolean targetLeft;

    public RunningLocController(@NonNull Context context, @NonNull RunningLocListener runningLocListener) {
        this.context = context;
        this.runningLocListener = runningLocListener;
        targetLeft = false;
        //初始化定位
        if (context != null) {
            initLocation();
        }

    }

    public RunningLocController(@NonNull Context context, @NonNull RunningLocListener runningLocListener
            , List<TargetPoint> targetPoints) {
        this.context = context;
        this.runningLocListener = runningLocListener;
        this.targetPoints = targetPoints;
        if (targetPoints != null && targetPoints.size() > 1)
            targetLeft = true;
        else
            targetLeft = false;
        //初始化定位
        if (context != null) {
            initLocation();
        }

    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(context);
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(aMaplocationListener);
    }

    /**
     * 定位监听
     */
    AMapLocationListener aMaplocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            runningLocListener.onLocationMove(loc);
            //目标点定位
            if (targetLeft){
                LatLng mylocation = LocationUtils.formatLatLng(loc);
                Iterator iterator  = targetPoints.iterator();
                while(iterator.hasNext()){
                    TargetPoint point = (TargetPoint)iterator.next();
                    if (LocationUtils.getDistance(point.getLatLng(), mylocation) < 50){
                        //抵达目标点，距离小于50米
                        runningLocListener.onTargetArrical(point);
                        iterator.remove();
                        break;
                    }
                }
                if (targetPoints.size()==0)
                    targetLeft = false;
            }
        }
    };

    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     */
    public void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

}
