package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Doctor;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *  This Recycler view displays the list of doctors returned from the API
 */

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors){
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
    holder.bindDoctor(mDoctors.get(position));
}

    @Override
    public int getItemCount(){
        return mDoctors.size();
    }

public class DoctorViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.firstNameTextView) TextView mFirstNameTextView;
    @Bind(R.id.practiceTextView) TextView mPracticeTextView;
    @Bind(R.id.bioTextView) TextView mBioTextView;

    private Context mContext;

    public DoctorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindDoctor(Doctor doctor) {
        mFirstNameTextView.setText(doctor.getName());
        mPracticeTextView.setText(doctor.getPracticeName());
        mBioTextView.setText(doctor.getBio());
    }
}
}
