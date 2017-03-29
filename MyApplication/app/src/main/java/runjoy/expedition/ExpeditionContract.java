package runjoy.expedition;

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

        /**
         * 在到达目的地后设定新的目标
         * @return 操作结果
         */
        boolean newCity(String city);

        /**
         * 更改当前的目的地
         * @return 操作结果
         */
        boolean modifyCity(String city);
    }
}
