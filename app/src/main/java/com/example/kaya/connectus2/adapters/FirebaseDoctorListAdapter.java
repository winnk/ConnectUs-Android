package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.kaya.connectus2.model.Doctor;
import com.example.kaya.connectus2.util.ItemTouchHelperAdapter;
import com.example.kaya.connectus2.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * TODO: Add a class header comment!
 */

public class FirebaseDoctorListAdapter extends FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder> implements ItemTouchHelperAdapter {

private DatabaseReference mRef;
private OnStartDragListener mOnStartDragListener;
private Context mContext;


public FirebaseDoctorListAdapter(Class<Doctor> modelClass, int modelLayout,
                                 Class<FirebaseDoctorViewHolder> viewHolderClass,
                                 Query ref, OnStartDragListener onStartDragListener, Context context) {
    super(modelClass, modelLayout, viewHolderClass, ref);
    mRef = ref.getRef();
    mOnStartDragListener = onStartDragListener;
    mContext = context;
   }

    @Override
    protected void populateViewHolder(final FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
        viewHolder.bindDoctor(model);
        Log.d("populateViewHolder","runs");
        viewHolder.mDoctorImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
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
}