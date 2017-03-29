package runjoy.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import runjoy.R;
import runjoy.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.layout_login_content);

        if (loginFragment == null) {
            loginFragment = loginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.layout_login_content);
        }
        new LoginPresenter_stub(loginFragment);

    }
}
