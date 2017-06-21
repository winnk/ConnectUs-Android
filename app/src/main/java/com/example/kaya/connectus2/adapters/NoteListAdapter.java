package com.example.kaya.connectus2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kaya.connectus2.R;
import com.example.kaya.connectus2.model.Note;
import com.example.kaya.connectus2.ui.NoteDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO: Add a class header comment!
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
private ArrayList<Note> mNotes = new ArrayList<>();
private Context mContext;

public NoteListAdapter(Context context, ArrayList<Note> notes) {
    mContext = context;
    mNotes = notes;
}

    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notes_list, parent, false);
    NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }


@Override
public void onBindViewHolder(NoteListAdapter.NoteViewHolder holder, int position) {
    holder.bindNote(mNotes.get(position));
}

@Override
public int getItemCount() { return mNotes.size(); }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.noteBodyTV) TextView mNoteBodyTV;
        @Bind(R.id.noteTitleTV) TextView mNoteTitleTV;

        private Context mContext;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindNote(Note note) {
            mNoteBodyTV.setText(note.getNote());
            mNoteTitleTV.setText(note.getTitle());

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NoteDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("doctors", Parcels.wrap(mNotes));
            mContext.startActivity(intent);
        }
     }
}