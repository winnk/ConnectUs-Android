package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * TODO: Add a class header comment!
 */

public class SymptomListAdapter extends ArrayAdapter {

private Context mContext;
private String[] mSymptoms;

public SymptomListAdapter(Context mContext, int resource, String[] mSymptoms) {
    super(mContext, resource);
    this.mContext = mContext;
    this.mSymptoms = mSymptoms;
}

@Override
public Object getItem(int position) {
    String symptom = mSymptoms[position];
    return String.format(symptom);
}

@Override
public int getCount() {
    return mSymptoms.length;
}
}