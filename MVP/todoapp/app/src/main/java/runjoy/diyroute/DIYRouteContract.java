package runjoy.diyroute;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface DIYRouteContract {

    interface View extends BaseView<Presenter> {

        void showMap();

        /**
         * 是否将路线点作为任务点
         */
        void showPointMissionDialog();

        /**
         * 系统提示信息
         */
        void showReminder();
    }

    interface Presenter extends BasePresenter {
        /**
         * 路线经过点设定
         * @return
         */
        boolean newpoint();

        /**
         * 完成路线设定
         * @return
         */
        boolean completeRoute();

        /**
         * 是否将路线点随机作为任务点
         */
        void pointAsMission();


    }
}
