package runjoy.register;

import runjoy.BasePresenter;
import runjoy.BaseView;
import runjoy.data.Account;

/**
 * Created by JiachenWang on 2017/1/11.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {


        // TODO: 2017/1/11
        //界面展示内容

    }

    interface Presenter extends BasePresenter {

        boolean saveAccount(Account account);

        boolean isAccountAvailable(String username);
    }
}
