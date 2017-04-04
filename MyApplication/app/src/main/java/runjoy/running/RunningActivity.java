package runjoy.running;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceOverlay;

import java.util.ArrayList;
import java.util.List;

import runjoy.R;
import runjoy.data.PathRecord;
import runjoy.data.TargetPoint;
import runjoy.tool.LocationUtils;
import runjoy.tool.NumberUtils;
import runjoy.tool.ToastUtil;
import runjoy.tool.TraceUtils;
import runjoy.tool.location.RunningLocController;
import runjoy.tool.location.RunningLocListener;
import runjoy.util.ActivityUtils;

/**
 * Created by JiachenWang on 2017/1/28
 */
public class RunningActivity extends AppCompatActivity implements RunningLocListener, TraceListener {

    private RunningContract.Presenter presenter;

    private FrameLayout layout_running_end_map;

    private RunningLocController runningLocController;
    private LocationSource.OnLocationChangedListener mListener;

    private AMap aMap;
    private MapView mapView;

    private AMap end_aMap;
    private MapView end_mapView;

    private Polyline mpolyline;
    private PolylineOptions mPolyoptions, tracePolytion;

    private List<TraceLocation> mTracelocationlist = new ArrayList<>();
    private List<TraceOverlay> mOverlayList = new ArrayList<>();
    private PathRecord record;
    private LatLng lastLoc = null;
    private int tracesize = 30;
    private int mDistance = 0;
    private TraceOverlay mTraceoverlay;
    private Marker mlocMarker;

    //TODO，结束用校对代码
//    runningLocController.stopLocation();
//    mOverlayList.add(mTraceoverlay);
//    LBSTraceClient mTraceClient = new LBSTraceClient(getApplicationContext());
//    mTraceClient.queryProcessedTrace(2, Util.parseTraceLocationList(record.getPathline()) , LBSTraceClient.TYPE_AMAP, MainActivity.this);
//    saveRecord(record.getPathline(), record.getDate());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.running_act);

        RunningFragment runningFragment = (RunningFragment) getSupportFragmentManager().findFragmentById(R.id.layout_running_content);

        if (runningFragment == null) {
            runningFragment = runningFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), runningFragment, R.id.layout_running_content);
        }
        presenter = new RunningPresenter_stub(runningFragment);


        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        end_mapView = (MapView) findViewById(R.id.map_endRunning);
        end_mapView.onCreate(savedInstanceState);

        initial();
        runningLocController.startLocation();

        layout_running_end_map = (FrameLayout) findViewById(R.id.layout_running_end_map);
        layout_running_end_map.setVisibility(View.GONE);
    }

    /**
     * 初始化AMap对象，locationController初始化
     */
    private void initial() {
        mDistance = 0;
        setUpMap();
        mTraceoverlay = new TraceOverlay(aMap);
        record = new PathRecord();
        //Intent 拿目标点信息
        List<TargetPoint> targetPoints = (List<TargetPoint>) getIntent().getSerializableExtra("targetPoints");
        if (targetPoints == null) {
            runningLocController = new RunningLocController(this.getApplicationContext(), RunningActivity.this);
        } else {
            runningLocController = new RunningLocController(this.getApplicationContext(), RunningActivity.this, targetPoints);
            for (TargetPoint point : targetPoints)
                this.addMarker(point);
        }
        initpolyline();

    }

    private void setUpMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            aMap.setMapTextZIndex(2);
        }
        if (end_aMap == null) {
            end_aMap = end_mapView.getMap();
            end_aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            end_aMap.setMapTextZIndex(2);
        }
    }

    public void stopRunning() {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLoc, 8));
        end_aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLoc, 8));
        runningLocController.stopLocation();
    }

    private void initpolyline() {
        mPolyoptions = new PolylineOptions();
        mPolyoptions.width(10f);
        mPolyoptions.color(Color.GRAY);
        tracePolytion = new PolylineOptions();
        tracePolytion.width(40);
        tracePolytion.setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.grasp_trace_line));
    }

    /**
     * 位置信息改变，设备移动
     *
     * @param amapLocation 改变的位置信息
     */
    @Override
    public void onLocationMove(AMapLocation amapLocation) {
        if (runningLocController != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                LatLng mylocation = LocationUtils.formatLatLng(amapLocation);
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(mylocation));
                record.addpoint(amapLocation);
                mPolyoptions.add(mylocation);
                mTracelocationlist.add(TraceUtils.parseTraceLocation(amapLocation));
                redrawline();
                updateLastLoc(mylocation);
                if (mTracelocationlist.size() > tracesize - 1) {
                    trace();
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * 抵达目标点
     *
     * @param target 目标点信息
     */
    @Override
    public void onTargetArrical(TargetPoint target) {
        //TODO，到达目标点的行为
        ToastUtil.show(RunningActivity.this, "到达目标点：" + target.getDescribe());
    }

    /**
     * 实时轨迹画线
     */
    private void redrawline() {
        if (mPolyoptions.getPoints().size() > 1) {
            if (mpolyline != null) {
                mpolyline.setPoints(mPolyoptions.getPoints());
            } else {
                mpolyline = aMap.addPolyline(mPolyoptions);
            }
        }
    }

    /**
     * 更新变量lastLoc
     *
     * @param loc
     */
    private void updateLastLoc(LatLng loc) {
        lastLoc = loc;
    }

    protected void saveRecord(List<AMapLocation> list, String time) {
        if (list != null && list.size() > 0) {
//            DbHepler = new DbAdapter(this);
//            DbHepler.open();
//            String duration = getDuration();
//            float distance = getDistance(list);
//            String average = getAverage(distance);
//            String pathlineSring = getPathLineString(list);
//            AMapLocation firstLocaiton = list.get(0);
//            AMapLocation lastLocaiton = list.get(list.size() - 1);
//            String stratpoint = amapLocationToString(firstLocaiton);
//            String endpoint = amapLocationToString(lastLocaiton);
//            DbHepler.createrecord(String.valueOf(distance), duration, average,
//                    pathlineSring, stratpoint, endpoint, time);
//            DbHepler.close();
            //TODO,记录数据
        } else {
            ToastUtil.showerror(RunningActivity.this, "错误：没有成功记录数据");
        }
    }

    /**
     * 绘制标记
     *
     * @param targetPoint
     */
    private void addMarker(TargetPoint targetPoint) {
        //展示Marker
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(targetPoint.getLatLng());
        markerOption.title("第" + targetPoint.getId() + "个目标点").snippet(targetPoint.getDescribe());

        markerOption.draggable(true);//设置Marker可拖动
        int pic;
        switch (targetPoint.getId()) {
            case 1:
                pic = R.drawable.mark_1;
                break;
            case 2:
                pic = R.drawable.mark_2;
                break;
            case 3:
                pic = R.drawable.mark_3;
                break;
            default:
                pic = R.drawable.mark_1;
        }
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), pic)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);
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
        runningLocController.stopLocation();
        super.onDestroy();
        mapView.onDestroy();
        runningLocController.destroyLocation();
    }

    private void trace() {
        List<TraceLocation> locationList = new ArrayList<>(mTracelocationlist);
        LBSTraceClient mTraceClient = new LBSTraceClient(getApplicationContext());
        mTraceClient.queryProcessedTrace(1, locationList, LBSTraceClient.TYPE_AMAP, this);
        TraceLocation lastlocation = mTracelocationlist.get(mTracelocationlist.size() - 1);
        mTracelocationlist.clear();
        mTracelocationlist.add(lastlocation);
    }

    /**
     * 轨迹纠偏失败回调。
     *
     * @param i
     * @param s
     */
    @Override
    public void onRequestFailed(int i, String s) {
        mOverlayList.add(mTraceoverlay);
        mTraceoverlay = new TraceOverlay(aMap);
    }

    @Override
    public void onTraceProcessing(int i, int i1, List<LatLng> list) {

    }

    /**
     * 轨迹纠偏成功回调。
     *
     * @param lineID      纠偏的线路ID
     * @param linepoints  纠偏结果
     * @param distance    总距离
     * @param waitingtime 等待时间
     */
    @Override
    public void onFinished(int lineID, List<LatLng> linepoints, int distance, int waitingtime) {
        if (lineID == 1) {
            if (linepoints != null && linepoints.size() > 0) {
                mTraceoverlay.add(linepoints);
                mDistance += distance;
                mTraceoverlay.setDistance(mTraceoverlay.getDistance() + distance);
                if (mlocMarker == null) {
                    mlocMarker = aMap.addMarker(new MarkerOptions().position(linepoints.get(linepoints.size() - 1))
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.point))
                            .title("距离：" + mDistance + "米"));
                    mlocMarker.showInfoWindow();
                } else {
                    mlocMarker.setTitle("距离：" + mDistance + "米");
                    ToastUtil.show(RunningActivity.this, "距离" + mDistance);
                    mlocMarker.setPosition(linepoints.get(linepoints.size() - 1));
                    mlocMarker.showInfoWindow();
                }
                presenter.startMonitor(NumberUtils.doubleStander(((double) mDistance) / 1000.0));
            }
        } else if (lineID == 2) {
            if (linepoints != null && linepoints.size() > 0) {
                aMap.addPolyline(new PolylineOptions()
                        .color(Color.RED)
                        .width(40).addAll(linepoints));
            }
        }

    }

    /**
     * 最后获取总距离
     *
     * @return
     */
    private int getTotalDistance() {
        int distance = 0;
        for (TraceOverlay to : mOverlayList) {
            distance = distance + to.getDistance();
        }
        return distance;
    }

    public void showEndMap() {
        layout_running_end_map.setVisibility(View.VISIBLE);
    }

}

