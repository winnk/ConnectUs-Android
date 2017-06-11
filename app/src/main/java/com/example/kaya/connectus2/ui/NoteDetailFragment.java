package com.example.kaya.connectus2.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kaya.connectus2.model.Note;

import com.example.kaya.connectus2.R;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class NoteDetailFragment extends Fragment {
@Bind(R.id.noteTitleTV) TextView mNoteTitleTV;
@Bind(R.id.noteBodyTV) TextView mNoteBodyTV;

 private Note mNote;

public NoteDetailFragment() {
    // Required empty public constructor
}

public static NoteDetailFragment newInstance(Note note) {
    NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable("note", Parcels.wrap(note));
    noteDetailFragment.setArguments(args);
    return noteDetailFragment;
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mNote = Parcels.unwrap(getArguments().getParcelable("note"));
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_notes_detail, container, false);
    ButterKnife.bind(this, view);

    mNoteTitleTV.setText(mNote.getTitle());
    mNoteBodyTV.setText(mNote.getNote());
    return view;
}

}
