package runjoy.expedition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import runjoy.R;
import runjoy.homepage.HomePageActivity;
import runjoy.settings.SettingsActivity;
import runjoy.startrunning.StartRunningActivity;
import runjoy.util.ActivityUtils;

public class ExpeditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expedition_act);

        ExpeditionFragment expeditionFragment = (ExpeditionFragment) getSupportFragmentManager().findFragmentById(R.id.layout_expeditioin_content);

        if (expeditionFragment == null) {
            expeditionFragment = expeditionFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), expeditionFragment, R.id.layout_expeditioin_content);
        }
        new ExpeditionPresenter_stub(expeditionFragment);

        Button btn_run = (Button) findViewById(R.id.btn_run);
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpeditionActivity.this, StartRunningActivity.class);
                startActivity(intent);
            }
        });

        Button btn_navibar_homepage = (Button) findViewById(R.id.btn_navibar_homepage);
        btn_navibar_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpeditionActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_navibar_setting = (Button) findViewById(R.id.btn_navibar_setting);
        btn_navibar_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpeditionActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
