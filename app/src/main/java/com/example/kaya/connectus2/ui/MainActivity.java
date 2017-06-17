package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.symptomSearchButton) ImageButton mSymptomSearchButton;
    @Bind(R.id.noteButton) ImageButton mNoteButton;
    @Bind(R.id.calendarButton) ImageButton mCalendarButton;
    @Bind(R.id.reminderButton) ImageButton mReminderButton;
    @Bind(R.id.mainTitleTV) TextView mMainTitleTV;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    mMainTitleTV = (TextView) findViewById(R.id.mainTitleTV);
    Typeface vanishFont = Typeface.createFromAsset(getAssets(), "fonts/vanish.ttf");
    mMainTitleTV.setTypeface(vanishFont);


    mSymptomSearchButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SearchSelect.class);
            startActivity(intent);
        }
    });


    mReminderButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
            startActivity(intent);
        }
    });


    mNoteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        }
    });


    mCalendarButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        }
    });


}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_logout) {
        logout();
        return true;
    }
    if (id == R.id.action_search) {
        Intent intent = new Intent(MainActivity.this, SearchSelect.class);
        startActivity(intent);
    }
    if (id == R.id.action_notes) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }
    if (id == R.id.action_reminder) {
       // Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
      //  startActivity(intent);

    }
    if (id == R.id.action_calendar) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }
    if (id == R.id.action_photo) {
        // future development
    }
    return super.onOptionsItemSelected(item);
}

private void logout() {
    FirebaseAuth.getInstance().signOut();
    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    finish();
}
}

