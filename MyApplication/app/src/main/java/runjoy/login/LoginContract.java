package runjoy.login;

import runjoy.BasePresenter;
import runjoy.BaseView;

/**
 * Created by JiachenWang on 2017/1/11.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        /**
         * 设置加载提示符
         */
        void showLoadingIndicator();

        /**
         * 设置出错信息
         */
        void showErrorInfo();

    }

    interface Presenter extends BasePresenter {

        boolean checkAccount(String username, String password);
    }
}
