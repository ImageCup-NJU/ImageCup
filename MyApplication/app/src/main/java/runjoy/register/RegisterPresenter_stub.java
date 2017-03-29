package runjoy.register;

import android.support.annotation.NonNull;

import runjoy.data.Account;

/**
 * Created by foxwel on 2017/3/15.
 */

public class RegisterPresenter_stub implements RegisterContract.Presenter {

    private RegisterContract.View mRegisterView;

    public RegisterPresenter_stub(@NonNull RegisterContract.View registerView) {
        mRegisterView = registerView;
        mRegisterView.setPresenter(this);
    }

    @Override
    public boolean saveAccount(Account account) {
        return true;
    }

    @Override
    public boolean isAccountAvailable(String username) {
        System.out.println("---"+username+"----");
        if (username.length() != 0) return true; else return false;
    }

    @Override
    public void start() {

    }
}
