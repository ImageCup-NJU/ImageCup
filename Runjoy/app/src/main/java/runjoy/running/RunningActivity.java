package runjoy.running;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import runjoy.running.location.LocationController;
import runjoy.running.location.LocationListener;
import runjoy.startrunning.StartRunningFragment;
import runjoy.startrunning.StartRunningPresenter_stub;
import runjoy.tool.Utils;
import runjoy.R;
import runjoy.util.ActivityUtils;

/**
 * Created by JiachenWang on 2017/1/28.
 */
public class RunningActivity extends AppCompatActivity implements LocationListener{

    private LocationController locationController;

    private AMap aMap;

    private MapView mapView;

    private LatLng lastLoc = null;

    private double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_act);

        RunningFragment runningFragment = (RunningFragment) getSupportFragmentManager().findFragmentById(R.id.layout_running_content);

        if (runningFragment == null) {
            runningFragment = runningFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), runningFragment, R.id.layout_running_content);
        }
        new RunningPresenter_stub(runningFragment);


        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        initial();
        locationController.startLocation();
    }

    /**
     * 初始化AMap对象，locationController初始化
     */
    private void initial() {
        distance = 0;
        setUpMap();
        locationController = new LocationController(this.getApplicationContext(), RunningActivity.this);
    }

    private void setUpMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(4));
            aMap.setMapTextZIndex(2);
        }
    }


    /**
     * 位置信息改变，设备移动
     * @param loc 改变的位置信息
     */
    @Override
    public void moveLocation(AMapLocation loc) {
        if (null != loc) {
            //定位结果转换
            LatLng current = Utils.formatLatLng(loc);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 17));
            addNewLine(current);
            addDistance(Utils.getDistance(lastLoc, Utils.formatLatLng(loc)));
            updateLastLoc(current);
        } else {
            Log.d("LogDemo", "定位失败！");
        }
    }

    /**
     * 绘制路径直线
     *
     * @param loc 当前位置
     */
    private void addNewLine(LatLng loc) {
        if (lastLoc == null) {
            lastLoc = loc;
        } else {
            //用一个数组来存放颜色，渐变色，四个点需要设置四个颜色
            List<Integer> colorList = new ArrayList<Integer>();
            colorList.add(Color.BLACK);//如果第四个颜色不添加，那么最后一段将显示上一段的颜色

            PolylineOptions options = new PolylineOptions();
            options.width(20);//设置宽度

            //加入四个点
            options.add(lastLoc, loc);

            //加入对应的颜色,使用colorValues 即表示使用多颜色，使用color表示使用单色线
            options.colorValues(colorList);

            //加上这个属性，表示使用渐变线
            options.useGradient(true);

            aMap.addPolyline(options);

        }
    }

    /**
     * 距离信息更新
     */
    private void addDistance(double newDistance){
//        info_text.setText(++count);
        distance = distance + newDistance;
        //TODO，界面有点问题还
        setTitle(distance + "");
    }

    /**
     * 更新变量lastLoc
     * @param loc
     */
    private void updateLastLoc(LatLng loc){
        lastLoc = loc;
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        locationController.stopLocation();
        super.onDestroy();
        mapView.onDestroy();
        locationController.destroyLocation();
    }

}

