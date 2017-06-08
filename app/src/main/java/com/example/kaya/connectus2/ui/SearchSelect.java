package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kaya.connectus2.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchSelect extends AppCompatActivity {
    @Bind(R.id.symptomSearchButton) Button mSymptomSearchButton;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_select);

    ButterKnife.bind(this);
    mSymptomSearchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String symptomSearchButton = mSymptomSearchButton.getText().toString();
            Intent intent = new Intent(SearchSelect.this, SymptomListActivity.class);
            startActivity(intent);
            Log.d("item is: ", symptomSearchButton );
        }
        });

}
}
