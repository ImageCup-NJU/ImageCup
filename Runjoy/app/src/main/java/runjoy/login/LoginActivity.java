package runjoy.login;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import runjoy.R;
import runjoy.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        if (loginFragment == null) {
            loginFragment = loginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.content);
        }
        new LoginPresenter(loginFragment);
    }
}
