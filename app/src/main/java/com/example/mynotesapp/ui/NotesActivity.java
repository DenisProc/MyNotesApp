package com.example.mynotesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mynotesapp.R;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        NotesFragmentOne notesFragment = new NotesFragmentOne();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_one_container,notesFragment).commit();

    }
}