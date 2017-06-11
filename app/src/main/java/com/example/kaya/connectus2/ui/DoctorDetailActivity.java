package com.example.kaya.connectus2.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.DoctorPagerAdapter;
import com.example.kaya.connectus2.model.Doctor;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private DoctorPagerAdapter adapterViewPager;
    ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);

        mDoctors = Parcels.unwrap(getIntent().getParcelableExtra("doctors"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new DoctorPagerAdapter(getSupportFragmentManager(), mDoctors);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}


