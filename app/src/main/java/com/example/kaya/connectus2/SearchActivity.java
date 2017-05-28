package com.example.kaya.connectus2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

private ImageButton mFindDoctorButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    mFindDoctorButton = (ImageButton) findViewById(R.id.findDoctorButton);
    mFindDoctorButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SearchActivity.this, FindDoctorActivity.class);
            startActivity(intent);
        }
    });

}
}
