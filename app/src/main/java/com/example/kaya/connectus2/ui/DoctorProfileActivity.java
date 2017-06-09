package com.example.kaya.connectus2.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kaya.connectus2.R;

public class DoctorProfileActivity extends AppCompatActivity {


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getActionBar().hide();
    findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Hello Snackbar", Snackbar.LENGTH_LONG).show();
        }
    });
}
}


