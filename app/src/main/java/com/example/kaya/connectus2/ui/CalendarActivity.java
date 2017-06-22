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

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


 public class CalendarActivity extends AppCompatActivity {

     TextView Day;
     MaterialCalendarView materialCalendarView;
     private boolean isClicked = false;
     Button viewButton,newEvent_button;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.calender_main);

         Button more = (Button)findViewById(R.id.more_button);
         newEvent_button = (Button)findViewById(R.id.new_event_button);
         viewButton = (Button)findViewById(R.id.viewButton);
         Day = (TextView)findViewById(R.id.text_day);
         materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);

         more.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(CalendarActivity.this, MoreActionAct.class);
                 startActivity(intent);
             }
         });

         materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
             @Override
             public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

//                Toast.makeText(CalendarActivity.this,date, Toast.LENGTH_SHORT).show();

                 Intent intent = new Intent(CalendarActivity.this, NewEventAct.class);
                 startActivity(intent);

             }
         });
     }


 }
