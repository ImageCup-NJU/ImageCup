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
         * 展示地图给用户作为任务选择点，上面画出了用户的路线
         * @param route map<经度,纬度>
         */
        void showRouteMap(Map<Double,Double> route);

        /**
         * 是否将路线点作为任务点，允许用户长按选择点
         */
        void showPointMissionDialog();

        /**
         * 系统提示信息
         * @param message    提示信息
         */
        void showReminder(String message);
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
         * 路线任务点设定
         * @param longitude    经度
         * @param latitude    纬度
         * @return
         */
        boolean newMissionPoint(double longitude,double latitude);

        /**
         * 完成路线设定
         * @return
         */
        boolean completeRoute();

        /**
         * 完成任务点设定
         * @return
         */
        boolean completeMissionP();

        /**
         * 确定将路线点随机作为任务点
         */
        void pointAsMission();


    }
}
