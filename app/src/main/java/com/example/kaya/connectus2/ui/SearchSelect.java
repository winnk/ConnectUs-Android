package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kaya.connectus2.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchSelect extends AppCompatActivity implements View.OnClickListener {
@Bind(R.id.symptomSearchButton)
Button mSymptomSearchButton;
@Bind(R.id.allDoctorsButton)
Button mAllDoctorsButton;
@Bind(R.id.crosshairs) ImageView crosshairs;
Animation animBounce;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_select);

    ButterKnife.bind(this);
    mSymptomSearchButton.setOnClickListener(this);
    mAllDoctorsButton.setOnClickListener(this);
    Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
    animation.setRepeatCount(Animation.INFINITE);
    crosshairs.startAnimation(animation );
}

@Override
public void onClick(View v) {
    if (v == mSymptomSearchButton) {
        String symptomSearchButton = mSymptomSearchButton.getText().toString();
        Intent intent = new Intent(SearchSelect.this, SymptomListActivity.class);
        startActivity(intent);
        Log.d("item is: ", symptomSearchButton);
    }
    if (v == mAllDoctorsButton) {
        Intent intent = new Intent(SearchSelect.this, SavedDoctorsListActivity.class);
        Log.d("alldocbutton", "intent to saved doc runs");
        startActivity(intent);
    }

}
}

