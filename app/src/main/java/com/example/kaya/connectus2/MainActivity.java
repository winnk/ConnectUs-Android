package com.example.kaya.connectus2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private ImageButton mSymptomSearchButton;
private ImageButton mReminderButton;
private ImageButton mNoteButton;
private ImageButton mCalendarButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSymptomSearchButton = (ImageButton) findViewById(R.id.symptomSearchButton);
    mSymptomSearchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
    });

    mReminderButton = (ImageButton) findViewById(R.id.reminderButton);
    mReminderButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
            startActivity(intent);
        }
    });

    mNoteButton = (ImageButton) findViewById(R.id.noteButton);
    mNoteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        }
    });

    mCalendarButton = (ImageButton) findViewById(R.id.calendarButton);
    mCalendarButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        }
    });


}
}

