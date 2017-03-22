package runjoy.startrunning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import runjoy.R;
import runjoy.util.ActivityUtils;

public class StartRunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startrunning_act);
        StartRunningFragment startRunningFragment = (StartRunningFragment) getSupportFragmentManager().findFragmentById(R.id.layout_startrunning_content);

        if (startRunningFragment == null) {
            startRunningFragment = startRunningFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), startRunningFragment, R.id.layout_startrunning_content);
        }
        new StartRunningPresenter_stub(startRunningFragment);
    }
}
