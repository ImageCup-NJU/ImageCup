package com.example.foxwel.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        final LinearLayout l2 = (LinearLayout) findViewById(R.id.hompage2_layout);
        final FrameLayout l1 = (FrameLayout) findViewById(R.id.hompage1_layout);
        l2.setVisibility(View.GONE);

        Button runButton = (Button) findViewById(R.id.runButton);
        runButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
        });

        Button cancelButton =(Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
            }
        });

        Button settingButton = (Button) findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,setting.class);
                startActivity(intent);
            }
        });

        Button tripButton = (Button) findViewById(R.id.tripButton);
        tripButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,expedition.class);
                startActivity(intent);
            }
        });


        ImageButton freeRunButton = (ImageButton) findViewById(R.id.freeRunButton);
        ImageButton userRunButton = (ImageButton) findViewById(R.id.userRunButton);

        freeRunButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,running.class);
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });

        userRunButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,running.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

    }
}
