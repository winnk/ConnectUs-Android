package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.util.ItemTouchHelperAdapter;
import com.example.kaya.connectus2.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 6/21/17.
 */

public class FirebaseNoteListAdapter extends FirebaseRecyclerAdapter<Note, FirebaseNoteViewHolder> implements ItemTouchHelperAdapter {

    private ChildEventListener mChildEventListener;
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ArrayList<Note> mNotes = new ArrayList<>();

    public FirebaseNoteListAdapter(Class<Note> modelClass, int modelLayout,
                                     Class<FirebaseNoteViewHolder> viewHolderClass,
                                     Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mNotes.add(dataSnapshot.getValue(Note.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void populateViewHolder(FirebaseNoteViewHolder viewHolder, Note model, int position) {
        viewHolder.bindNote(model);
        Log.d("populateViewHolder","runs");
       /* viewHolder..setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
        */
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Log.d("onItemMove","runs");
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
        Log.d("onItemDismiss","runs");
    }

    private void setIndexInFirebase() {
        for (Note note : mNotes) {
            int index = mNotes.indexOf(note);
            DatabaseReference ref = getRef(index);
            note.setIndex(Integer.toString(index));
            ref.setValue(note);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}

