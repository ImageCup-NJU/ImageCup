package runjoy.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import runjoy.R;
import runjoy.util.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_act);
        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.layout_register_content);

        if (registerFragment == null) {
            registerFragment = registerFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), registerFragment, R.id.layout_register_content);
        }
        new RegisterPresenter_stub(registerFragment);
    }
}
