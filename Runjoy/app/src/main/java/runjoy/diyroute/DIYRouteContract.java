package runjoy.diyroute;

import java.util.Map;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface DIYRouteContract {

    interface View extends BaseView<Presenter> {

        /**
         * 展示地图给用户选择路线点，允许用户长按选择点
         */
        void showMap();

        /**
         * 系统提示信息
         * @param message    提示信息
         */
        void showReminder(String message);

        /**
         * 开始跑步，跳转到running界面
         */
        void startRun();
    }

    interface Presenter extends BasePresenter {
        /**
         * 路线经过点设定
         * @param longitude    经度
         * @param latitude    纬度
         * @return
         */
        boolean newpoint(double longitude,double latitude);

        /**
         * 完成路线设定
         * @return
         */
        boolean completeRoute();


    }
}
