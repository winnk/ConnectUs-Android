package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.kaya.connectus2.R;

import butterknife.Bind;
import butterknife.ButterKnife;


 public class CalendarActivity extends AppCompatActivity {

     private  static final String TAG = "CalendarActivity";
     private CalendarView mCalendarView;

     //private static final String TAG = "CalendarActivity";

     private TextView thedate;
     private Button btngocalendar;

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

         mCalendarView = (CalendarView) findViewById(R.id.calendarView);
         mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                 String date = year + "/" + month + "/"+ dayOfMonth ;
                 Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                 Intent intent = new Intent(CalendarActivity.this,MainActivity.class);
                 intent.putExtra("date",date);
                 startActivity(intent);

             }
         });
     }
 }
