package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.kaya.connectus2.R;


public class SettingsAct extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        RelativeLayout Remainder = (RelativeLayout)findViewById(R.id.remainder_layout);
        RelativeLayout Timezone = (RelativeLayout)findViewById(R.id.TimeZone_layout);

        Remainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SettingsAct.this, RemainderAct.class);
                startActivity(a);
            }
        });
        Timezone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(SettingsAct.this,TimeZoneAct.class);
                startActivity(a);
            }
        });
    }
}


