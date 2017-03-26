package runjoy.running;

import android.support.annotation.NonNull;

import java.util.Map;

import runjoy.data.TemporaryData;
import runjoy.data.source.RouteDataSource;
import runjoy.tool.enums.RunModeEnum;

/**
 * Created by xiaomai on 2017/1/25.
 */

public class RunningPresenter implements RunningContract.Presenter {

    @NonNull
    private final RunningContract.View mRunningView;

    @NonNull
    private final RouteDataSource mRouteDataSource;

    private Map<Double,Double> route;

    private int missionNum;

    private RunModeEnum mode;

    public RunningPresenter(@NonNull RunningContract.View runningView,@NonNull RouteDataSource routeDataSource){
        mRunningView=runningView;
        mRouteDataSource=routeDataSource;

        mode=TemporaryData.runMode;
        missionNum=0;
    }

    @Override
    public void startRun() {
        mRunningView.showMap(mode,route);
        mRunningView.showCompleteMission(missionNum);
    }

    @Override
    public void endRun(Long useTime) {

    }

    @Override
    public void delayMessage() {

    }

    @Override
    public void newMission() {

    }

    @Override
    public void completeMission(String result) {

    }

    @Override
    public void startMonitor() {

    }

    @Override
    public void start() {
        if (TemporaryData.runMode== RunModeEnum.DIYRun){
            route=TemporaryData.route;
        }
        startRun();
    }
}
