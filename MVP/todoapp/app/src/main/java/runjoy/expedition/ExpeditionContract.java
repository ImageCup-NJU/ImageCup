package runjoy.expedition;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface ExpeditionContract {

    interface View extends BaseView<Presenter> {

        /**
         * 地图形式展示当前进度
         */
        void showMap();

        /**
         * 当前进度的展示说明（路线全长，已跑路程，剩余路程，已用时间等）
         */
        void showDetail();

        /**
         * 已抵达的目标
         */
        void showHistory();

    }

    interface Presenter extends BasePresenter {

    }
}
