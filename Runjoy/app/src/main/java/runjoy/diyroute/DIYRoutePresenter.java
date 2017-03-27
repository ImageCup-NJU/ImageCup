package runjoy.diyroute;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;

import runjoy.data.TemporaryData;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lenovo on 2017/1/24.
 */

public class DIYRoutePresenter implements DIYRouteContract.Presenter {

    private final DIYRouteContract.View diyRouteView;

    public DIYRoutePresenter(@NonNull DIYRouteContract.View diyrouteView){
        diyRouteView=checkNotNull(diyrouteView, "diyRouteView cannot be null!");
        diyRouteView.setPresenter(this);
    }

    @Override
    public boolean newpoint(double longitude, double latitude) {
        if(TemporaryData.route==null){
            TemporaryData.route=new LinkedHashMap<>();
        }
        if (!ifActive(longitude,latitude)){
            diyRouteView.showReminder("这个位置点无效哦");
            return false;
        }
        TemporaryData.route.put(longitude,latitude);
        return true;
    }

    public boolean ifActive(double longitude, double latitude){
        return true;
    }

    @Override
    public boolean completeRoute() {
        diyRouteView.startRun();
        return true;
    }

    @Override
    public void start() {

    }
}
