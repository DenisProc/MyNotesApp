package com.example.mynotesapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Notes implements Parcelable {
    private static int counter;
    public static ArrayList<Notes> notesArrayList = new ArrayList<Notes>();


    public int getId() {
        return id;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    int id;
    String noteName;
    String date;
    String noteDescription;

    public static ArrayList<Notes> getNotesArrayList() {
        return notesArrayList;
    }

    public Notes() {

    }

    public Notes(String name, String date, String noteDescription) {
        this.noteName = name;
        this.date = date;
        this.noteDescription = noteDescription;
    }
    {
        id = ++counter;
    }

    protected Notes(Parcel in) {
        id = in.readInt();
        noteName = in.readString();
        date = in.readString();
        noteDescription = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getNoteName());
        parcel.writeString(getDate());
        parcel.writeString(getNoteDescription());
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }
        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };
}
