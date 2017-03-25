package runjoy.running;

import android.support.annotation.NonNull;

import runjoy.startrunning.StartRunningContract;
import runjoy.tool.enums.RunModeEnum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class RunningPresenter_stub implements RunningContract.Presenter{

    @NonNull
    private final RunningContract.View mRunningView;


    public RunningPresenter_stub(@NonNull RunningContract.View runningView){
        mRunningView = checkNotNull(runningView);
        mRunningView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void startRun() {

    }

    @Override
    public void endRun() {

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
}
