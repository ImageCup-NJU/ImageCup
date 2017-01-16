package runjoy.expedition;

import java.util.List;
import java.util.Map;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.City;
import runjoy.data.Route;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface ExpeditionContract {

    interface View extends BaseView<Presenter> {

        /**
         * 地图形式展示当前进度以及相关信息
         * @param route    当前跑步信息
         */
        void showMap(Route route);


        /**
         * 历史已抵达的目标
         * @param city    历史跑步信息
         */
        void showHistory(City city);

    }

    interface Presenter extends BasePresenter {

    }
}
