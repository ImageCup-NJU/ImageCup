package com.example.foxwel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class expedition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedition);

        final ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);

        final LinearLayout historyLayout = (LinearLayout) findViewById(R.id.history);
        historyLayout.setVisibility(View.GONE);

        Button settingButton = (Button) findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expedition.this,setting.class);
                startActivity(intent);
            }
        });

        Button homepageButton = (Button) findViewById(R.id.homepageButton);
        homepageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(expedition.this,homepage.class);
                startActivity(intent);
            }
        });

        ImageButton historyButton = (ImageButton) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                historyLayout.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                historyLayout.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);
            }
        });

    }
}
