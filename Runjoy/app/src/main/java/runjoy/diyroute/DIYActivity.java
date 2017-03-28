package runjoy.diyroute;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.util.List;

import runjoy.R;
import runjoy.tool.ToastUtil;

/**
 * Created by JiachenWang on 2017/1/28.
 */
public class DIYActivity extends Activity implements AMap.OnMapClickListener,
        AMap.OnMarkerClickListener, GeocodeSearch.OnGeocodeSearchListener {

    private AMap aMap;
    private MapView mapView;
    GeocodeSearch geocoderSearch;
    ProgressDialog progressDialog;

    LatLng onclick;
    private List<LatLng> list = null;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc_activity);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        initial();
        //TODO,先这么显示
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.060021, 118.787976), 17));
    }

    /**
     * 初始化AMap对象
     */
    private void initial() {
        setUpMap();
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        progressDialog = new ProgressDialog(this);

    }

    private void setUpMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.moveCamera(CameraUpdateFactory.zoomTo(4));
            aMap.setMapTextZIndex(2);
            aMap.setOnMapClickListener(this);
            aMap.setOnMarkerClickListener(this);
        }

    }

    /**
     * 绘制标记
     *
     * @param pos
     * @param level
     * @param describe
     */
    private void addMarker(LatLng pos, int level, String describe) {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(pos);
        markerOption.title("第" + level + "个目标点").snippet(describe);

        markerOption.draggable(true);//设置Marker可拖动
        int pic;
        switch (level) {
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
        count++;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (count <= 3) {
            onclick = latLng;
            getAddress(latLng);
        } else {
            ToastUtil.show(DIYActivity.this, "至多3个目标点");
        }

    }

    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLng latLng) {
        showDialog();
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude, latLng.longitude), 100,
                GeocodeSearch.AMAP);
        // 设置异步逆地理编码请求
        geocoderSearch.getFromLocationAsyn(query);
    }

    /**
     * 逆地理编码
     *
     * @param regeocodeResult
     * @param i
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        dismissDialog();
        String address;
        if (i == AMapException.CODE_AMAP_SUCCESS) {
            if (regeocodeResult != null && regeocodeResult.getRegeocodeAddress() != null
                    && regeocodeResult.getRegeocodeAddress().getFormatAddress() != null) {
                address = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                address = address.substring(address.indexOf("街道") + 2);
                ToastUtil.show(DIYActivity.this, address);
                //添加目标点
                addMarker(onclick, count, address);
            } else {
                ToastUtil.show(DIYActivity.this, "查无结果");
            }
        } else {
            ToastUtil.showerror(DIYActivity.this, "警告:查询错误");
        }
    }

    /**
     * 地理编码
     *
     * @param geocodeResult
     * @param i
     */
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("正在获取信息");
        progressDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
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
        super.onDestroy();
        mapView.onDestroy();
    }

}
