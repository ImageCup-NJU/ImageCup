package runjoy.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import runjoy.R;
import runjoy.achievement.AchievementActivity;
import runjoy.expedition.ExpeditionActivity;
import runjoy.settings.SettingsActivity;
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

        Button btn_navibar_expedtion = (Button) findViewById(R.id.btn_navibar_expedtion);



        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, StartRunningActivity.class);
                startActivity(intent);
            }
        });


        btn_navibar_expedtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ExpeditionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_navibar_setting = (Button) findViewById(R.id.btn_navibar_setting);
        btn_navibar_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_navibar_achievement = (Button) findViewById(R.id.btn_navibar_achievement);
        btn_navibar_achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, AchievementActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
