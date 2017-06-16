package com.example.kaya.connectus2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.FirebaseDoctorViewHolder;
import com.example.kaya.connectus2.model.Doctor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDoctorsListActivity extends AppCompatActivity {
private DatabaseReference mDoctorReference;
private FirebaseRecyclerAdapter mFirebaseAdapter;

@Bind(R.id.recyclerView) RecyclerView mRecyclerView;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_doctor);
    ButterKnife.bind(this);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    mDoctorReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS).child(uid);
    setUpFirebaseAdapter();
}

private void setUpFirebaseAdapter() {
    mFirebaseAdapter = new FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder>
            (Doctor.class, R.layout.doctor_list_item, FirebaseDoctorViewHolder.class,
                    mDoctorReference) {

        @Override
        protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {viewHolder.bindDoctor(model);
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