package runjoy.expedition;

import java.util.List;
import java.util.Map;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface ExpeditionContract {

    interface View extends BaseView<Presenter> {

        /**
         * 地图形式展示当前进度
         * @param start   出发地
         * @param end   目的地
         */
        void showMap(String start,String end);

        /**
         * 当前进度的展示说明（路线全长，已跑路程，剩余路程，已用时间等）
         * @param allDistance    路线全长
         * @param distance      已跑路程
         * @param time      已用时间
         */
        void showDetail(double allDistance,double distance,long time);

        /**
         * 历史已抵达的目标
         * @param route   出发地，目的地
         * @param time   用时
         */
        void showHistory(Map<String,String> route,List<String> time);

    }

    interface Presenter extends BasePresenter {

    }
}
