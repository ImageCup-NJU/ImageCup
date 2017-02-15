package com.example.foxwel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class running extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);

        final FrameLayout freeRunningLayout = (FrameLayout) findViewById(R.id.free_running);
        final FrameLayout userRunningLayout = (FrameLayout) findViewById(R.id.user_running);
        final LinearLayout freeRunningEndLayout = (LinearLayout) findViewById(R.id.free_running_end);
        final LinearLayout userRunningEndLayout = (LinearLayout) findViewById(R.id.user_running_end);
        final LinearLayout missionLayout = (LinearLayout) findViewById(R.id.mission);


        if (type == 0)
        {
            userRunningLayout.setVisibility(View.GONE);
            freeRunningEndLayout.setVisibility(View.GONE);
            userRunningEndLayout.setVisibility(View.GONE);
        }

        if (type == 1)
        {
            freeRunningLayout.setVisibility(View.GONE);
            freeRunningEndLayout.setVisibility(View.GONE);
            userRunningEndLayout.setVisibility(View.GONE);
        }

        Button acceptButton = (Button) findViewById(R.id.acceptButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button waitButton = (Button) findViewById(R.id.waitButton);
        ImageButton freeRunningStopButton = (ImageButton) findViewById(R.id.freeRunningStop);
        ImageButton userRunningStopButton = (ImageButton) findViewById(R.id.userRunningStop);
        Button freeExitButton = (Button) findViewById(R.id.freeExitButton);
        Button userExitButton = (Button) findViewById(R.id.userExitButton);
        Button freeShowInDistanceButton = (Button) findViewById(R.id.freeShowInDistanceButton);
        Button userShowInDistanceButton = (Button) findViewById(R.id.userShowInDistanceButton);


        acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                missionLayout.setVisibility(View.GONE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                missionLayout.setVisibility(View.GONE);
            }
        });

        waitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                missionLayout.setVisibility(View.GONE);
            }
        });

        freeRunningStopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                freeRunningLayout.setVisibility(View.GONE);
                freeRunningEndLayout.setVisibility(View.VISIBLE);
            }
        });

        userRunningStopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userRunningLayout.setVisibility(View.GONE);
                userRunningEndLayout.setVisibility(View.VISIBLE);
            }
        });

        freeExitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(running.this,homepage.class);
                startActivity(intent);
            }
        });

        userExitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(running.this,homepage.class);
                startActivity(intent);
            }
        });

        freeShowInDistanceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(running.this,homepage.class);
                startActivity(intent);
            }
        });

        userShowInDistanceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(running.this,expedition.class);
                startActivity(intent);
            }
        });
    }
}
