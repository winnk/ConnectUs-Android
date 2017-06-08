package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NotesActivity extends AppCompatActivity implements View.OnClickListener {
// private Button mSaveNoteButton;
// private EditText mNotebodyET;

private DatabaseReference mFirebaseNoteBody;

@Bind(R.id.saveNoteButton)
Button mSaveNoteButton;
@Bind(R.id.noteBodyET)
EditText mNoteBodyET;

@Override
protected void onCreate(Bundle savedInstanceState) {

    mFirebaseNoteBody = FirebaseDatabase
            .getInstance()
            .getReference()
            .child(Constants.BD_NOTE_BODY);

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);
    ButterKnife.bind(this);

    mSaveNoteButton.setOnClickListener(this);
}

@Override
public void onClick(View v) {
    if (v == mSaveNoteButton) {
        String note = mNoteBodyET.getText().toString();

        saveNoteToFirebase(note);

        Intent intent = new Intent(NotesActivity.this, NotesListActivity.class);
        Log.d("notes save", note);
        startActivity(intent);
    }
}

public void saveNoteToFirebase(String note) {
    mFirebaseNoteBody.push().setValue(note);
    }

}


