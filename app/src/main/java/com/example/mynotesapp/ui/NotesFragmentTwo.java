package com.example.mynotesapp.ui;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.Notes;
import com.google.android.material.button.MaterialButton;

public class NotesFragmentTwo extends Fragment {
    static final String SELECTED_NOTE = "SELECTED_NOTE";
    Notes note;

    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (note == null)
            note = Notes.getNotesArrayList().get(0);
        outState.putParcelable(SELECTED_NOTE, note);
        super.onSaveInstanceState(outState);
    }

    public NotesFragmentTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_notes_two, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        MaterialButton button = view.findViewById(R.id.note_save_and_back);
        button.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().popBackStack());

        if (arguments != null) {

            Notes paramNote = (Notes) arguments.getParcelable(SELECTED_NOTE);

            for (int i = 0; i < Notes.notesArrayList.size(); i++) {
                if (Notes.notesArrayList.get(i).getId() == paramNote.getId()) {
                    note = Notes.notesArrayList.get(i);
                }
            }

            TextView tvName = view.findViewById(R.id.note_name_redactor);
            tvName.setText(note.getNoteName());
            tvName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setNoteName(tvName.getText().toString());
                    updateData();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

            TextView tvDescription = view.findViewById(R.id.note_desc_redactor);
            tvDescription.setText(note.getNoteDescription());
            tvDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setNoteDescription(tvDescription.getText().toString());
                    updateData();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemDelete = menu.findItem(R.id.action_delete_note);
        MenuItem itemCreateNewNote = menu.findItem(R.id.action_create_new_note);
        MenuItem itemFind = menu.findItem(R.id.action_find);
        if (itemDelete != null) {
            itemDelete.setVisible(true);
        }
        if (itemCreateNewNote != null) {
            itemCreateNewNote.setVisible(false);
        }
        if (itemFind != null) {
            itemFind.setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete_note) {
            Notes.notesArrayList.remove(note);
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.fragment_one_container, new NotesFragmentOne()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateData() {
        NotesFragmentOne notesFragment = (NotesFragmentOne) requireActivity().getSupportFragmentManager().getFragments().stream().filter(fragment -> fragment instanceof NotesFragmentOne)
                .findFirst().get();

        notesFragment.initDisplay();
    }

    public static NotesFragmentTwo newInstance(Notes note) {
        NotesFragmentTwo fragment = new NotesFragmentTwo();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}