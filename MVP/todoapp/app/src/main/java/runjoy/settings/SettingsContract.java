package runjoy.settings;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        /**
         * 设置修改结果提示
         */
        void showResultIndicator();

    }

    interface Presenter extends BasePresenter {
        // TODO: 2017/1/11 个人偏好设置 
    }

}
