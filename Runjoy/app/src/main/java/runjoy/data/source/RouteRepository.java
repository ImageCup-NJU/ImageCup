package runjoy.data.source;

import android.support.annotation.NonNull;

import runjoy.data.City;
import runjoy.data.Route;
import runjoy.data.RunInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lenovo on 2017/1/24.
 */

public class RouteRepository implements RouteDataSource{

    private static RouteRepository INSTANCE = null;

    private final RouteDataSource mRouteLocalDataSource;

    private RouteRepository(@NonNull RouteDataSource routeLocalDataSource) {
        mRouteLocalDataSource = checkNotNull(routeLocalDataSource);
    }

    /**
     * 单例模式
     * @param routeLocalDataSource
     * @return
     */
    public static RouteRepository getInstance(RouteDataSource routeLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RouteRepository(routeLocalDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void getLastRun(@NonNull GetRunCallback callback) {
        mRouteLocalDataSource.getLastRun(callback);
    }

    @Override
    public void getMyRoute(@NonNull GetRouteCallback callback) {
        mRouteLocalDataSource.getMyRoute(callback);
    }

    @Override
    public City getHistoryRoute() {

        return mRouteLocalDataSource.getHistoryRoute();
    }

    @Override
    public void saveRun(@NonNull RunInfo runInfo) {
        mRouteLocalDataSource.saveRun(runInfo);
    }

    @Override
    public void updateRoute(@NonNull Route route) {
        mRouteLocalDataSource.updateRoute(route);
    }

    @Override
    public void saveRoute(@NonNull Route route) {
        mRouteLocalDataSource.saveRoute(route);
    }
}
