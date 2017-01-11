package runjoy.newcity;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.City;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface NewCityContract {

    interface View extends BaseView<Presenter> {
        /**
         * 展示地图
         */
        void showMap();

        /**
         * 展示当前目的地信息
         */
        void showDetail();

    }

    interface Presenter extends BasePresenter {
        /**
         * 在到达目的地后设定新的目标
         * @return 操作结果
         */
        boolean newCity(City city);

        /**
         * 更改当前的目的地
         * @return 操作结果
         */
        boolean modifyCity(City city);
    }
}
