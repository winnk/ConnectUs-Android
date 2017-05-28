package com.example.kaya.connectus2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
    private ImageButton mFindDoctorButton;
    private EditText mSymptomEditText;
    private ListView mListView;
    private String[] symptoms = new String[]{"bleeding","bruising","chills and shivering","convulsions","deformity","discharge","dizziness","dry mouth ","epistaxis","fatigue","hyperthermia","hypothermia","jaundice", "loss of appetite","muscle cramps","muscle weakness","pain - abdominal","pain - chest ","pyrexia","swelling","syncope","tinnitus","tremor","weight gain","weight loss" };

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    mFindDoctorButton = (ImageButton) findViewById(R.id.findDoctorButton);
    mListView = (ListView) findViewById(R.id.symptomsListView);



    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, symptoms);
    mListView.setAdapter(adapter);




}
}
