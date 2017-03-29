package runjoy.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import runjoy.R;
import runjoy.expedition.ExpeditionActivity;
import runjoy.homepage.HomePageActivity;
import runjoy.startrunning.StartRunningActivity;
import runjoy.util.ActivityUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_act);

        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.layout_settings_content);

        if (settingsFragment == null) {
            settingsFragment = settingsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), settingsFragment, R.id.layout_settings_content);
        }
        new SettingsPresenter_stub(settingsFragment);

        Button btn_run = (Button) findViewById(R.id.btn_run);

        Button btn_navibar_homepage = (Button) findViewById(R.id.btn_navibar_homepage);

        Button btn_navibar_expedition = (Button) findViewById(R.id.btn_navibar_expedtion);

        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, StartRunningActivity.class);
                startActivity(intent);
            }
        });

        btn_navibar_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_navibar_expedition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ExpeditionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
