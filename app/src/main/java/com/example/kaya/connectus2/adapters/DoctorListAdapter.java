package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Doctor;
import com.example.kaya.connectus2.ui.DoctorDetailActivity;
import com.example.kaya.connectus2.ui.DoctorListActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 *  This Recycler view displays the list of doctors returned from the API
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {

private static final int MAX_WIDTH = 200;
private static final int MAX_HEIGHT = 200;


private ArrayList<Doctor> mDoctors = new ArrayList<>();
private Context mContext;

public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
    mContext = context;
    mDoctors = doctors;

}

@Override
public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
    DoctorViewHolder viewHolder = new DoctorViewHolder(view);
    return viewHolder;
}

@Override
public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
    holder.bindDoctor(mDoctors.get(position));
}


@Override
public int getItemCount() {
    return mDoctors.size();
}

public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Bind(R.id.firstNameTV)TextView mFirstNameTV;
    @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
   // @Bind(R.id.lastNameTV)TextView mLastNameTV;
    //@Bind(R.id.practiceTV)TextView mPracticeTextView;
    // @Bind(R.id.nameTextView)TextView mNameTextView;
    //@Bind(R.id.specialtyTextView) TextView mSpecialtyTV;
    //@Bind(R.id.bioTextView) TextView mBioTextView;

    private Context mContext;

    public DoctorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }


    public void bindDoctor(Doctor doctor) {

       Picasso.with(mContext)
               .load(doctor.getImageUrl())
               .resize(MAX_WIDTH, MAX_HEIGHT)
               .centerCrop()
               .into(mDoctorImageView);

         mFirstNameTV.setText(doctor.getFirstName()+ " "+(doctor.getLastName()));
    }

    @Override
    public void onClick(View v) {
        Log.d("click", "DoctorListAdapter click runs");
        int itemPosition = getLayoutPosition();

        Intent intent = new Intent(mContext, DoctorDetailActivity.class);
        intent.putExtra("position", itemPosition);
        intent.putExtra("doctors", Parcels.wrap(mDoctors));

        mContext.startActivity(intent);
    }
}
}

