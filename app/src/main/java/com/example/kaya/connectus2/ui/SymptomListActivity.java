package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaya.connectus2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SymptomListActivity extends AppCompatActivity {

@Bind(R.id.symptomsListView) ListView mListView;

    private String[] symptoms = new String[]{"bleeding","bruising","chills and shivering","convulsions","deformity","discharge","dizziness","dry mouth ","epistaxis","fatigue","hyperthermia","hypothermia","jaundice", "loss of appetite","muscle cramps","muscle weakness","pain - abdominal","pain - chest ","pyrexia","swelling","syncope","tinnitus","tremor","weight gain","weight loss" };

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_symptom_list);
    ButterKnife.bind(this);

    SymptomListAdapter adapter = new SymptomListAdapter(this, android.R.layout.simple_list_item_1, symptoms);
    mListView.setAdapter(adapter);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String symptom = ((TextView) view).getText().toString();
            Intent intent = new Intent(SymptomListActivity.this, DoctorListActivity.class);
            intent.putExtra("symptom", symptom);
            //intent.putExtra("parent", "symptom");
            startActivity(intent);
        }
    });

}
}
