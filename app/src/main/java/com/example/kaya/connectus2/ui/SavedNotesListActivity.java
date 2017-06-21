package com.example.kaya.connectus2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.FirebaseNoteListAdapter;
import com.example.kaya.connectus2.adapters.FirebaseNoteViewHolder;
import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.util.OnStartDragListener;
import com.example.kaya.connectus2.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedNotesListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mNoteReference;
    private FirebaseNoteListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SavedNoteOnCreate", "runs");

        setContentView(R.layout.activity_saved_notes_list);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        Log.d("setupFirebaseAdapster","runs");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_NOTES).child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);


        mFirebaseAdapter = new FirebaseNoteListAdapter(Note.class, R.layout.notes_list_item, FirebaseNoteViewHolder.class, query, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Log.d("onStartDrag","runs");
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
        protected void onDestroy() {
            super.onDestroy();
             mFirebaseAdapter.cleanup();
     }
}




