package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kaya.connectus2.R;

public class FindDoctorActivity extends AppCompatActivity {
    private TextView mSymptomTextView;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_find_doctor);
    mSymptomTextView = (TextView) findViewById(R.id.symptomTextView);

    Intent intent = getIntent();
    String symptom = intent.getStringExtra("symptom");
    mSymptomTextView.setText("Here are all the physicians who treat your symptom: " + symptom);

}
}
