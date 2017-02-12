package runjoy.homepage;

import android.support.annotation.NonNull;

import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.data.source.RouteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xiaomai on 2017/1/24.
 */

public class HomePagePresenter implements HomePageContract.Presenter {

    @NonNull
    private final RouteDataSource mRouteDataSource;

    @NonNull
    private final HomePageContract.View mHomePageView;

    private RunInfo lastRunInfo;

    private Route route;

    public HomePagePresenter(@NonNull RouteDataSource routeDataSource,@NonNull HomePageContract.View homePageView){
        mRouteDataSource=checkNotNull(routeDataSource);
        mHomePageView=checkNotNull(homePageView);
    }

    @Override
    public void start() {
        showInfo();
    }

    //拿到数据，传到view
    public void showInfo(){
        mRouteDataSource.getLastRun(new RouteDataSource.GetRunCallback(){

            @Override
            public void onRunLoaded(RunInfo runInfo) {
                lastRunInfo=runInfo;
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        mRouteDataSource.getMyRoute(new RouteDataSource.GetRouteCallback(){
            @Override
            public void onRouteLoaded(Route mRoute) {
                route=mRoute;
            }
            @Override
            public void onDataNotAvailable() {

            }
        });
        mHomePageView.showLastRun(lastRunInfo);
        mHomePageView.showMyTrip(route);
    }
}
