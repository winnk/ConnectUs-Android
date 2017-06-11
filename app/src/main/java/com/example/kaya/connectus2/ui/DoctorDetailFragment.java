package com.example.kaya.connectus2.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class DoctorDetailFragment extends Fragment implements View.OnClickListener {

   @Bind(R.id.doctorNameTextView)
    TextView mNameLabel;
    @Bind(R.id.specialtyTextView) TextView mSpecialtyLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.address1TV) TextView mAddress1Label;
    @Bind(R.id.address3TV) TextView mAddress3Label;
    @Bind(R.id.bioTextView) TextView mBioLabel;
    @Bind(R.id.saveDoctorButton) Button mSaveDoctorButton;
    @Bind(R.id.doctorImageView) ImageView mImageLabel;

private Doctor mDoctor;

public static DoctorDetailFragment newInstance (Doctor doctor) {
    Log.d("DoctorDetailFragment","newInstances called");

    DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable("doctor", Parcels.wrap(doctor));
    doctorDetailFragment.setArguments(args);
    return doctorDetailFragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    Log.d("DoctorDetailFragment","onCreate runs");
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
    ButterKnife.bind(this, view);

    Picasso.with(view.getContext()).load(mDoctor.getImageUrl()).into(mImageLabel);

    mNameLabel.setText(mDoctor.getFirstName() + " " + mDoctor.getLastName());
    mSpecialtyLabel.setText(mDoctor.getSpecialty());
    mAddress1Label.setText(mDoctor.getAddress1() + " " + mDoctor.getAddress2());
    mAddress3Label.setText(mDoctor.getCity() + ", " + mDoctor.getState() + ", " + mDoctor.getZip());
    mPhoneLabel.setText(android.text.TextUtils.join(", ", mDoctor.getPhone()));
    mBioLabel.setText(mDoctor.getBio());

    mImageLabel.setOnClickListener(this);
    mAddress1Label.setOnClickListener(this);
    mAddress3Label.setOnClickListener(this);
    mSaveDoctorButton.setOnClickListener(this);
    Log.d("fragment", "fragment_doctor_detail onCreateView loads");
    return view;
}

@Override
public void onClick(View v) {

    if (v == mSaveDoctorButton) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference doctorRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid);

        DatabaseReference pushRef = doctorRef.push();
        String pushId = pushRef.getKey();
        mDoctor.setPushId(pushId);
        pushRef.setValue(mDoctor);

        Toast.makeText(getContext(), "Doctor Saved!", Toast.LENGTH_SHORT).show();
    }
 }

}