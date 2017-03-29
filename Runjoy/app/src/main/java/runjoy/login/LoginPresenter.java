package runjoy.login;

import android.support.annotation.NonNull;

/**
 * Created by foxwel on 2017/3/15.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @Override
    public boolean checkAccount(String username, String password) {
        return false;
    }

    @Override
    public void start() {

    }
}
