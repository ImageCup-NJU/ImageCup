package runjoy.running;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import runjoy.data.RunInfo;

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
        mRunningView.showCompleteMission(0);
    }

    @Override
    public void startRun() {
        mRunningView.showMessageDialog("!!前方有一只小猫已经三天没有吃东西了，正眼巴巴地望着你给它喂食，它心满意足地吃饱以后，可以为你的行程增加贡献哦！");
    }

    @Override
    public void endRun(Long useTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RunInfo runInfo = null;
        try {
            runInfo = new RunInfo(14.2,useTime,2,20.2,20, dateFormat.parse("2015-3-13"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mRunningView.showEndInfo(runInfo);
    }

    @Override
    public void delayMessage() {

    }

    @Override
    public void newMission() {
        mRunningView.showCompleteMission(1);
    }

    @Override
    public void completeMission(String result) {

    }

    @Override
    public void startMonitor() {
        mRunningView.showDistance(0);
    }
}
