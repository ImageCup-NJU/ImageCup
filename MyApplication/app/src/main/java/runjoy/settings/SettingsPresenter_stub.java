package runjoy.settings;

import android.support.annotation.NonNull;

import runjoy.data.Account;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by foxwel on 2017/3/22.
 */

public class SettingsPresenter_stub implements SettingsContract.Presenter{

    @NonNull
    private final SettingsContract.View mSettingsView;


    public SettingsPresenter_stub(@NonNull SettingsContract.View settingsView){
        mSettingsView = checkNotNull(settingsView);
        mSettingsView.setPresenter(this);
    }

    @Override
    public boolean saveUserInfo(String userName, String password, double distance) {
        return true;
    }

    @Override
    public void start() {
        Account account = new Account("Foxwel", 14, 25.5, 0.5, "foxwel@nju.edu.cn");
        mSettingsView.showUserInfo(account);
    }
}
