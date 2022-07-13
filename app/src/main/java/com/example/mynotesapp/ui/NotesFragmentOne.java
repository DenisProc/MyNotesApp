package com.example.mynotesapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.Notes;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


public class NotesFragmentOne extends Fragment {
    private final ArrayList<Notes> notesArrayList = new ArrayList<Notes>();
    MaterialButton createNewNoteBtn;
    private int addIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createNewNoteBtn = (MaterialButton) view.findViewById(R.id.create_new_note);
        createNewNoteBtn.setOnClickListener(v -> {
            notesArrayList.add(new Notes("Новая заметка", "13.07.2022", "Описание заметки"));
            initList(view);
            addIndex++;
        });
    }

    private void initList(View view) {
        LinearLayout container = (LinearLayout) view;
        for (int i = addIndex; i < notesArrayList.size(); i++) {
            View itemView = getLayoutInflater().inflate(R.layout.cardview_notes, container, false);
            TextView textViewNoteName = itemView.findViewById(R.id.note_name);
            textViewNoteName.setText(notesArrayList.get(i).getNoteName());
            TextView textViewNoteDate = itemView.findViewById(R.id.note_date);
            textViewNoteDate.setText(notesArrayList.get(i).getDate());

            final int index = i;
            itemView.setOnClickListener(cardViewClick ->
                    noteBuilder(index));
            container.addView(itemView);
        }
    }

    private void noteBuilder(int index) {
        if (isLandscape()) {
            noteBuilderLand(index);
        } else {
            noteBuilderPort(index);
        }
    }

    private void noteBuilderPort(int index) {
        NotesFragmentTwo notesFragmentTwo = NotesFragmentTwo.newInstance(index);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_one_container, notesFragmentTwo)
                .addToBackStack("")
                .commit();
    }

    private void noteBuilderLand(int index) {
        NotesFragmentTwo notesFragmentTwo = NotesFragmentTwo.newInstance(index);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_two_container, notesFragmentTwo)
                .addToBackStack("")
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}