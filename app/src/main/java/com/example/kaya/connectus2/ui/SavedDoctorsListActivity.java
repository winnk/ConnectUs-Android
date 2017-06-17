package com.example.kaya.connectus2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.FirebaseDoctorListAdapter;
import com.example.kaya.connectus2.adapters.FirebaseDoctorViewHolder;
import com.example.kaya.connectus2.model.Doctor;
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

public class SavedDoctorsListActivity extends AppCompatActivity implements OnStartDragListener {

//private DatabaseReference mDoctorReference;
private FirebaseDoctorListAdapter mFirebaseAdapter;
private ItemTouchHelper mItemTouchHelper;

@Bind(R.id.recyclerView)RecyclerView mRecyclerView;

@Override
protected void onCreate(Bundle savedInstanceState) {
    Log.d("SavedDoctorListActivity","runs");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_doctor);
    ButterKnife.bind(this);

    setUpFirebaseAdapter();
}

private void setUpFirebaseAdapter() {
    Log.d("setupFirebaseAdapster","runs");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS).child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);

    mFirebaseAdapter = new FirebaseDoctorListAdapter(Doctor.class, R.layout.doctor_list_item_drag, FirebaseDoctorViewHolder.class, query, this, this);

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
    Log.d("onDestroy","runs");
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}


