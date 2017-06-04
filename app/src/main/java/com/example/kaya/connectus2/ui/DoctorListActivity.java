package com.example.kaya.connectus2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.adapters.DoctorListAdapter;
import com.example.kaya.connectus2.model.Doctor;
import com.example.kaya.connectus2.services.BDService;
import java.io.IOException;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorListActivity extends AppCompatActivity {

    @Bind(R.id.textView6) TextView mTextView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    private String parentString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchSymptom = intent.getStringExtra("params");
        parentString = intent.getStringExtra("parent");

        mTextView.setText("Results for \"" + searchSymptom + "\"");
        getDoctors(searchSymptom);
    }

    private void getDoctors(String searchSymptom){

        final BDService bdService = new BDService();
        BDService.findBySymptom(searchSymptom, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) { e.printStackTrace(); }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = bdService.processResults(response);

                DoctorListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DoctorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }

                    ;
                });
            }
        });
    }
}

