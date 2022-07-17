package com.example.mynotesapp.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.Notes;
import com.google.android.material.button.MaterialButton;

public class NotesFragmentTwo extends Fragment {
    static final String SELECTED_NOTE = "SELECTED_NOTE";
    Notes note;

    public NotesFragmentTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

            TextView tvTitle = view.findViewById(R.id.note_name_redactor);
            tvTitle.setText(note.getNoteName());
            tvTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setNoteName(tvTitle.getText().toString());
                    updateData();
                    //Note.getNotes()[index].setTitle(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

            TextView tvDescription = view.findViewById(R.id.note_desc_redactor);
            tvDescription.setText(note.getNoteDescription());
        }
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
}