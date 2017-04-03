package runjoy.achievement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import runjoy.R;
import runjoy.expedition.ExpeditionActivity;
import runjoy.homepage.HomePageActivity;
import runjoy.settings.SettingsActivity;
import runjoy.startrunning.StartRunningActivity;
import runjoy.util.ActivityUtils;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement_act);

        AchievementFragment achievementFragment = (AchievementFragment) getSupportFragmentManager().findFragmentById(R.id.layout_achievement_content);

        if (achievementFragment == null) {
            achievementFragment = achievementFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), achievementFragment, R.id.layout_achievement_content);
        }
        new AchievementPresenter_stub(achievementFragment);

        Button btn_run = (Button) findViewById(R.id.btn_run);

        Button btn_navibar_homepage = (Button) findViewById(R.id.btn_navibar_homepage);

        Button btn_navibar_expedition = (Button) findViewById(R.id.btn_navibar_expedtion);

        Button btn_navibar_setting = (Button) findViewById(R.id.btn_navibar_setting);

        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, StartRunningActivity.class);
                startActivity(intent);
            }
        });

        btn_navibar_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_navibar_expedition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, ExpeditionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_navibar_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
