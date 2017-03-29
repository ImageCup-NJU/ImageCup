package runjoy.diyroute;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

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
        if(TemporaryData.getInstance().getRoute()==null){
            Map<Double,Double> map=new LinkedHashMap<Double,Double>();
            TemporaryData.getInstance().setRoute(map);
        }
        if (!ifActive(longitude,latitude)){
            diyRouteView.showReminder("这个位置点无效哦");
            return false;
        }
        TemporaryData.getInstance().getRoute().put(longitude,latitude);
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
