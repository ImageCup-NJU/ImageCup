package runjoy.startrunning;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.homepage.HomePageContract;
import runjoy.tool.enums.RunModeEnum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class StartRunningPresenter_stub implements StartRunningContract.Presenter{

    @NonNull
    private final StartRunningContract.View mStartRunningView;


    public StartRunningPresenter_stub(@NonNull StartRunningContract.View startRunningView){
        mStartRunningView = checkNotNull(startRunningView);
        mStartRunningView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void selectMode(RunModeEnum mode) {

    }
}
