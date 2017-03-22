package runjoy.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import runjoy.R;
import runjoy.startrunning.StartRunningActivity;
import runjoy.util.ActivityUtils;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_act);
        HomepageFragment homepageFragment = (HomepageFragment) getSupportFragmentManager().findFragmentById(R.id.layout_homepage_content);

        if (homepageFragment == null) {
            homepageFragment = homepageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homepageFragment, R.id.layout_homepage_content);
        }
        new HomePagePresenter_stub(homepageFragment);

        Button btn_run = (Button) findViewById(R.id.btn_run);
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, StartRunningActivity.class);
                startActivity(intent);
            }
        });
    }
}
