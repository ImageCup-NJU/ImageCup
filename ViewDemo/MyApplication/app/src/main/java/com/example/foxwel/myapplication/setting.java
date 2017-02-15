package com.example.foxwel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        Button tripButton = (Button) findViewById(R.id.tripButton);
        tripButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,expedition.class);
                startActivity(intent);
            }
        });

        Button homepageButton = (Button) findViewById(R.id.homepageButton);
        homepageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this,homepage.class);
                startActivity(intent);
            }
        });
    }
}
