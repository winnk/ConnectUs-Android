package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.ui.SavedNotesListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO: Add a class header comment!
 */

public class FirebaseNoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

private static final int MAX_WIDTH = 200;
private static final int MAX_HEIGHT = 200;

public ImageView mNoteImageView;

View mView;
Context mContext;



public FirebaseNoteViewHolder(View itemView) {
    super(itemView);
    mView = itemView;
    mContext = itemView.getContext();
    itemView.setOnClickListener(this);
}

public void bindNote(Note note) {
    TextView noteBody = (TextView) mView.findViewById(R.id.noteBodyTV);
    TextView noteTitle = (TextView) mView.findViewById(R.id.noteTitleTV);
    mNoteImageView = (ImageView) mView.findViewById(R.id.noteImageView);

    //TextView noteDate = (TextView) mView.findViewById(R.id.noteDateTV);

// add after notes view working
   // Picasso.with(mContext).load(note.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(noteImageView);

    noteBody.setText(note.getNote());
    noteTitle.setText(note.getTitle());
  // noteDate.setText(note.getDate());
}

// ADD WHEN CAMERA ADDED
//public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
  //  byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
  // return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
//}

@Override
public void onClick(View view) {
    Log.d("FBNoteViewHolder", "runs");
    final ArrayList<Note> notes = new ArrayList<>();

      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      String uid = user.getUid();
      DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_NOTES);

      noteRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    notes.add(snapshot.getValue(Note.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SavedNotesListActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("notes", Parcels.wrap(notes));

                mContext.startActivity(intent);
            }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    });
 }
}