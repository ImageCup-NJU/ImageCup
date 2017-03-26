package runjoy.homepage;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.Account;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.data.Weather;

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

        /**
         * 展示天气信息
         * @param weather
         */
        void showWeather(Weather weather);

        /**
         * 展示用户信息
         * @param account
         */
        void showUserInfo(Account account);
    }

    interface Presenter extends BasePresenter {

    }
}
