package com.example.kaya.connectus2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.FirebaseNoteViewHolder;
import com.example.kaya.connectus2.model.Note;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedNotesListActivity extends AppCompatActivity {
private DatabaseReference mNoteReference;
private FirebaseRecyclerAdapter mFirebaseAdapter;

@Bind(R.id.recyclerView)RecyclerView mRecyclerView;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes_list);
    ButterKnife.bind(this);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    mNoteReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_NOTES).child(uid);
    setUpFirebaseAdapter();
}

private void setUpFirebaseAdapter() {
    mFirebaseAdapter = new FirebaseRecyclerAdapter<Note, FirebaseNoteViewHolder>
            (Note.class, R.layout.notes_list_item, FirebaseNoteViewHolder.class, mNoteReference) {


        @Override
        protected void populateViewHolder(FirebaseNoteViewHolder viewHolder, Note model, int position) {
            viewHolder.bindNote(model);
        }
    };
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(mFirebaseAdapter);
}

@Override
    protected void onDestroy() {
        super.onDestroy();
         mFirebaseAdapter.cleanup();
 }
}




