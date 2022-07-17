package com.example.mynotesapp.ui;

import static com.example.mynotesapp.ui.NotesFragmentTwo.SELECTED_NOTE;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.Notes;
import com.google.android.material.button.MaterialButton;


public class NotesFragmentOne extends Fragment {
    Notes notes;
    MaterialButton createNewNoteBtn;
    private int addIndex = 0;
    View dataContainer;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(SELECTED_NOTE, notes);
        super.onSaveInstanceState(outState);
    }


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

        if (savedInstanceState != null) {
            notes = (Notes) savedInstanceState.getParcelable(SELECTED_NOTE);
        }
        dataContainer = view.findViewById(R.id.fragment_one_container);
        initDisplay(dataContainer);

/*        createNewNoteBtn = (MaterialButton) view.findViewById(R.id.create_new_note);
        createNewNoteBtn.setOnClickListener(v -> {
            Notes.notesArrayList.add(new Notes("Заметка","дата","Описание"));

            addIndex++;
        });

 */
    }

    public void initDisplay() {
        initDisplay(dataContainer);
    }

    public void initDisplay(View view) {
        LinearLayout container = (LinearLayout) view;
        container.removeAllViews();
        initCreateBtn(container);
        initList(container);
    }

    private void noteBuilder(Notes notes) {
        this.notes = notes;
        if (isLandscape()) {
            noteBuilderLand(notes);
        } else {
            noteBuilderPort(notes);
        }
    }

    private void noteBuilderPort(Parcelable parcelable) {
        NotesFragmentTwo notesFragmentTwo = NotesFragmentTwo.newInstance(notes);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_one_container, notesFragmentTwo)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void noteBuilderLand(Parcelable parcelable) {
        NotesFragmentTwo notesFragmentTwo = NotesFragmentTwo.newInstance(notes);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_two_container, notesFragmentTwo)
                .addToBackStack("")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(LinearLayout container) {
        for (int i = 0; i < Notes.getNotesArrayList().size(); i++) {
            View itemView = getLayoutInflater().inflate(R.layout.cardview_notes, container, false);
            TextView textViewNoteName = itemView.findViewById(R.id.note_name);
            textViewNoteName.setText(Notes.getNotesArrayList().get(i).getNoteName());
            TextView textViewNoteDate = itemView.findViewById(R.id.note_date);
            textViewNoteDate.setText(Notes.getNotesArrayList().get(i).getDate());

            final int index = i;
            itemView.setOnClickListener(cardViewClick ->
                    noteBuilder(Notes.getNotesArrayList().get(index)));
            container.addView(itemView);
        }
    }

    private void initCreateBtn(LinearLayout container) {
        View btnCreate = getLayoutInflater().inflate(R.layout.create_new_note_btn, container, false);
        btnCreate.setOnClickListener(vBtn -> {
            Notes.notesArrayList.add(new Notes("Заметка", "дата", "Описание"));
            initDisplay();
        });
        container.addView(btnCreate);
    }
}