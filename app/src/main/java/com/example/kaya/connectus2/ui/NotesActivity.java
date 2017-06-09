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
public String mNote;

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
        String mNote = mNoteBodyET.getText().toString();

        InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();
        StringBuilder errMsg = new StringBuilder("Unable to save. Please fix the following and try again\n");

//Validate and Save
        boolean allowSave = true;
        //calls validator helper class
        if (inputValidatorHelper.isNullOrEmpty(mNote)) {
            errMsg.append("- Note should not be empty.\n");
            Toast.makeText(NotesActivity.this, errMsg,
                    Toast.LENGTH_SHORT).show();
            allowSave = false;
        }

        if(allowSave){
            //Proceeds with save logic

            String note = mNoteBodyET.getText().toString();

            saveNoteToFirebase(note);

            Intent intent = new Intent(NotesActivity.this, NotesListActivity.class);
            Log.d("notes save", note);
            startActivity(intent);
        }
    }
}

public void saveNoteToFirebase(String note) {
    mFirebaseNoteBody.push().setValue(note);
    }


}


