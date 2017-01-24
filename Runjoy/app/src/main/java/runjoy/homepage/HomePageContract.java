package runjoy.homepage;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.Route;
import runjoy.data.RunInfo;

/**
 * Created by xiaomai on 2017/1/24.
 */

public interface HomePageContract {

    interface View extends BaseView<Presenter> {

        /**
         * 展示上次跑步信息
         * @param runInfo
         */
        void showLastRun(RunInfo runInfo);

        /**
         * 展示我的里程
         * @param route
         */
        void showMyTrip(Route route);
    }

    interface Presenter extends BasePresenter {

    }
}
