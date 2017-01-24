package runjoy.homepage;

import android.support.annotation.NonNull;

import runjoy.data.source.RouteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lenovo on 2017/1/24.
 */

public class HomePagePresenter implements HomePageContract.Presenter {

    @NonNull
    private final RouteDataSource mTouteDataSource;

    @NonNull
    private final HomePageContract.View mHomePageView;

    public HomePagePresenter(@NonNull RouteDataSource routeDataSource,@NonNull HomePageContract.View homePageView){

        mTouteDataSource=checkNotNull(routeDataSource);
        mHomePageView=checkNotNull(homePageView);

    }

    @Override
    public void start() {
        showInfo();
    }

    //拿到数据，传到view
    public void showInfo(){

    }
}
