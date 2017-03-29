package runjoy.startrunning;

import runjoy.data.TemporaryData;
import runjoy.tool.enums.RunModeEnum;

/**
 * Created by tian on 2017/3/28.
 */

public class StartRunningPresenter implements StartRunningContract.Presenter {


    @Override
    public void selectMode(RunModeEnum mode) {
        TemporaryData.getInstance().setRunMode(mode);
    }

    @Override
    public void start() {

    }
}
