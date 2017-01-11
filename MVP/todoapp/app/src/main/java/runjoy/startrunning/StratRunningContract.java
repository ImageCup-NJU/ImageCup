package runjoy.startrunning;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.tool.enums.RunModeEnum;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface StratRunningContract {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {
        /**
         * 选择跑步模式
         */
        void selectMode(RunModeEnum mode);
    }
}
