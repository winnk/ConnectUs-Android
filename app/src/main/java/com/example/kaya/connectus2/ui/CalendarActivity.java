package com.example.kaya.connectus2.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kaya.connectus2.R;

import butterknife.Bind;
import butterknife.ButterKnife;


 public class CalendarActivity extends AppCompatActivity {

    // @Bind(R.id.calendarTitleTV)
     TextView mCalendarTitleTV;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_calendar);

         ButterKnife.bind(this);

         //mCalendarTitleTV = (TextView) findViewById(R.id.calendarTitleTV);
         Typeface vanishFont = Typeface.createFromAsset(getAssets(), "fonts/vanish.ttf");
        // mCalendarTitleTV.setTypeface(vanishFont);


     }
 }
