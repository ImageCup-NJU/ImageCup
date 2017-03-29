package runjoy.expedition;

import android.support.annotation.NonNull;

import runjoy.data.City;
import runjoy.data.Route;
import runjoy.data.source.RouteDataSource;

/**
 * Created by xiaomai on 2017/1/25.
 */

public class ExpeditionPresenter implements ExpeditionContract.Presenter{

    @NonNull
    private final ExpeditionContract.View mExpeditionView;

    @NonNull
    private final RouteDataSource mRouteDataSource;

    private Route route;

    private City historyRoute;

    public ExpeditionPresenter(@NonNull ExpeditionContract.View expeditionView, @NonNull RouteDataSource routeDataSource){
        mExpeditionView=expeditionView;
        mRouteDataSource=routeDataSource;
    }

    @Override
    public boolean newCity(String city) {
        return false;
    }

    @Override
    public boolean modifyCity(String city) {
        return false;
    }

    @Override
    public void start() {
        showExpedition();
    }

    public void showExpedition(){
        mRouteDataSource.getMyRoute(new RouteDataSource.GetRouteCallback(){
            @Override
            public void onRouteLoaded(Route mRoute) {
                route=mRoute;
            }
            @Override
            public void onDataNotAvailable() {

            }
        });
        historyRoute=mRouteDataSource.getHistoryRoute();
        mExpeditionView.showMap(route);
        mExpeditionView.showHistory(historyRoute);
    }
}
