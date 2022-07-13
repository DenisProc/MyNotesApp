package com.example.mynotesapp.model;

import android.widget.EditText;

public class Notes {
    String noteName;
    String date;
    String noteDescription;


    public Notes(String name, String date, String noteDescription) {
        this.noteName = name;
        this.date = date;
        this.noteDescription = noteDescription;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getDate() {
        return date;
    }

    public String getNoteDescription() {
        return noteDescription;
    }
}
