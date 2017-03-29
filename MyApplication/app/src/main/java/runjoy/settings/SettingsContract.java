package runjoy.settings;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.Account;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        /**
         * 设置修改结果提示
         */
        void showResultIndicator();

        /**
         *
         * @param account 用户账户信息对象
         */
        void showUserInfo(Account account);

    }

    interface Presenter extends BasePresenter {
        // TODO: 2017/1/11 个人偏好设置

        /**
         * @param userName
         * @param password
         * @param distance 意在每跑多少公里处接受任务
         * @return
         */
        boolean saveUserInfo(String userName, String password, double distance);
    }

}
