package com.example.kaya.connectus2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.ui.NoteDetailFragment;

import java.util.ArrayList;

/**
 * TODO: Add a class header comment!
 */

public class NotePagerAdapter extends FragmentPagerAdapter {
private ArrayList<Note> mNotes;

public NotePagerAdapter(FragmentManager fm, ArrayList<Note> doctors) {
    super(fm);
    mNotes = doctors;
}

@Override
public Fragment getItem(int position) {
    return NoteDetailFragment.newInstance(mNotes.get(position));
}

@Override
public int getCount() {
    return mNotes.size();
}

@Override
public CharSequence getPageTitle(int position) {
    return mNotes.get(position).getTitle();
}
}
