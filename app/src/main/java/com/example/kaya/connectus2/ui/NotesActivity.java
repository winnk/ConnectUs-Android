package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.Helpers.InputValidatorHelper;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NotesActivity extends AppCompatActivity implements View.OnClickListener {
// private Button mSaveNoteButton;
// private EditText mNotebodyET;

private DatabaseReference mFirebaseNoteBody;

@Bind(R.id.saveNoteButton)Button mSaveNoteButton;
@Bind(R.id.noteBodyTV)EditText mNoteBodyET;
@Bind(R.id.noteTitleET) EditText mNoteTitle;


@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes);
    ButterKnife.bind(this);

    mSaveNoteButton.setOnClickListener(this);
}

@Override
public void onClick(View v) {

    if (v == mSaveNoteButton) {
        String noteBody = mNoteBodyET.getText().toString();
        String noteTitle = mNoteTitle.getText().toString();

// Calls validator
        InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();
        StringBuilder errMsg = new StringBuilder("Unable to save ");

//Validate and Save
        boolean allowSave = true;

//calls validator helper class
        if (inputValidatorHelper.isNullOrEmpty(noteTitle) || inputValidatorHelper.isNullOrEmpty(noteBody)) {
            errMsg.append("- Title and note should not be empty.\n");
            Toast.makeText(NotesActivity.this, errMsg,
                    Toast.LENGTH_SHORT).show();
            allowSave = false;
        }

        if(allowSave){
//Proceeds with save logic

            Note note = new Note(noteBody, noteTitle);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference noteRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_NOTES)
                    .child(uid);

            DatabaseReference pushRef = noteRef.push();
            String pushId = pushRef.getKey();
            note.setPushId(pushId);
            pushRef.setValue(note);
            Log.d("saved:", noteTitle);
            Toast.makeText(NotesActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            ResetFields();

            Intent intent = new Intent(NotesActivity.this, SavedNotesListActivity.class);
            startActivity(intent);
        }
    }
}

public void ResetFields() {
    mNoteBodyET.setText("");
    mNoteTitle.setText("");
}
}




