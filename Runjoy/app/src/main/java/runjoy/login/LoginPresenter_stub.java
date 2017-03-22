package runjoy.login;

import android.support.annotation.NonNull;

/**
 * Created by foxwel on 2017/3/15.
 */

public class LoginPresenter_stub implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    public LoginPresenter_stub(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public boolean checkAccount(String username, String password) {
        mLoginView.showLoadingIndicator();
        return true;
    }

    @Override
    public void start() {

    }
}
