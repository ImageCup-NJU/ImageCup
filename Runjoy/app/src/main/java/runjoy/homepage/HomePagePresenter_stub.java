package runjoy.homepage;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import runjoy.data.Route;
import runjoy.data.RunInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class HomePagePresenter_stub implements HomePageContract.Presenter {

    @NonNull
    private final HomePageContract.View mHomePageView;

    private RunInfo lastRunInfo;

    private Route route;

    public HomePagePresenter_stub(@NonNull HomePageContract.View homePageView){
        mHomePageView=checkNotNull(homePageView);
        homePageView.setPresenter(this);
    }

    @Override
    public void start() {
        showInfo();
    }

    public void showInfo(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RunInfo runInfo = null;
        try {
            runInfo = new RunInfo(14.2,100000,2,20.2,"为了达到目的地做了贡献", dateFormat.parse("2015-3-13"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Route route = new Route(1, "南京","北京", 114, 20, 155525, 0);
        mHomePageView.showLastRun(runInfo);
        mHomePageView.showMyTrip(route);
    }
}
