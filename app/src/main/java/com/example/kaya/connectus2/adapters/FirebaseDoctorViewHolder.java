package com.example.kaya.connectus2.adapters;

/**
 * TODO: Add a class header comment!
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Doctor;
import com.example.kaya.connectus2.ui.DoctorDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

private static final int MAX_WIDTH = 200;
private static final int MAX_HEIGHT = 200;

View mView;
Context mContext;

public FirebaseDoctorViewHolder(View itemView) {
    super(itemView);
    mView = itemView;
    mContext = itemView.getContext();
    itemView.setOnClickListener(this);
}

public void bindDoctor(Doctor doctor) {
    TextView firstNameTV = (TextView) mView.findViewById(R.id.firstNameTV);
    ImageView doctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);

    Picasso.with(mContext)
            .load(doctor.getImageUrl())
            .resize(MAX_WIDTH, MAX_HEIGHT)
            .centerCrop()
            .into(doctorImageView);

    firstNameTV.setText(doctor.getName());
  }

@Override
public void onClick(View view) {
    final ArrayList<Doctor> doctors = new ArrayList<>();


    // check assignment on this to see if this should be here
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS).child(uid);



    doctorRef.addListenerForSingleValueEvent(new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                doctors.add(snapshot.getValue(Doctor.class));
            }
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DoctorDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("doctors", Parcels.wrap(doctors));

            mContext.startActivity(intent);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
}

