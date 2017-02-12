package runjoy.data.source;

import android.support.annotation.NonNull;

import runjoy.data.City;
import runjoy.data.Route;
import runjoy.data.RunInfo;

/**
 * Created by lenovo on 2017/1/24.
 */

public class RouteRepository implements RouteDataSource{


    @Override
    public void getLastRun(@NonNull GetRunCallback callback) {

    }

    @Override
    public void getMyRoute(@NonNull GetRouteCallback callback) {

    }

    @Override
    public City getHistoryRoute() {
        return null;
    }

    @Override
    public void saveRun(@NonNull RunInfo runInfo) {

    }

    @Override
    public void updateRoute(@NonNull Route route) {

    }

    @Override
    public void saveRoute(@NonNull Route route) {

    }
}
