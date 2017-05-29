package com.example.kaya.connectus2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;


public class NotesActivity extends AppCompatActivity {
    private Button mSaveNoteButton;
    private EditText mNoteEditText;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);
    mSaveNoteButton = (Button) findViewById(R.id.saveNoteButton);
    mNoteEditText = (EditText) findViewById(R.id.noteEditText);

   mSaveNoteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mNoteEditText.getText().toString().trim().length() > 0) {
                Toast.makeText(NotesActivity.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(NotesActivity.this, NotesListActivity.class);
            startActivity(intent);
        }
    });
}
}


