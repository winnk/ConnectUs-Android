package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.Helpers.InputValidatorHelper;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NotesActivity extends AppCompatActivity implements View.OnClickListener {
// private Button mSaveNoteButton;
// private EditText mNotebodyET;

private DatabaseReference mFirebaseNoteBody;
private static final int REQUEST_IMAGE_CAPTURE = 111;

@Bind(R.id.noteImageView) ImageView mImageLabel;
@Bind(R.id.saveNoteButton)Button mSaveNoteButton;
@Bind(R.id.allNotesButton)Button mAllNotesButton;
@Bind(R.id.noteBodyTV)EditText mNoteBodyET;
@Bind(R.id.noteTitleET) EditText mNoteTitle;
@Bind(R.id.photoButton) ImageButton mPhotoButton;

// to hold image data
// private ImageView mImageView;
//private Bitmap mImageBitmap;

@Override
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note);
    ButterKnife.bind(this);

    //setHasOptionsMenu(true);

    mSaveNoteButton.setOnClickListener(this);
    mAllNotesButton.setOnClickListener(this);
    mPhotoButton.setOnClickListener(this);
    }

public void onLaunchCamera() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        Log.d("onLaunchCamera", "on launch runs");
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==RESULT_OK) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        mImageLabel.setImageBitmap(imageBitmap);
        //encodeBitmapAndSaveToFirebase(imageBitmap);
        Log.d("onActivityResult", "activityResult runs");
    }
}

@Override
public void onClick(View v) {
    if (v == mAllNotesButton) {
        Toast.makeText(NotesActivity.this, "clicked",
                Toast.LENGTH_SHORT).show();
        Log.d("mAllNotesButton", "points to SavedNotes");
       Intent intent = new Intent(NotesActivity.this, SavedNotesListActivity.class);
       startActivity(intent);
       }

    if (v == mPhotoButton){
        Toast.makeText(NotesActivity.this, "clicked",
                Toast.LENGTH_SHORT).show();
        Log.d("mPhotoButton", "runs");
        onLaunchCamera();
        }

    if (v == mSaveNoteButton) {
        String noteBody = mNoteBodyET.getText().toString();
        String noteTitle = mNoteTitle.getText().toString();
        Log.d("mSaveNoteButton", "is pressed");
        Log.d("mSaveButton", noteBody);

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


        if (allowSave) {
        //Proceeds with save logic
            Log.d("allowSave", "runs");
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

 public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
    String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    DatabaseReference ref = FirebaseDatabase.getInstance()
            .getReference(Constants.FIREBASE_CHILD_NOTES)
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            //.child(mNotes.getPushId()) // FIGURE OUT WHAT GOES HERE
            .child("imageUrl");
    ref.setValue(imageEncoded);
}


public void ResetFields() {
    mNoteBodyET.setText("");
    mNoteTitle.setText("");
}
}




