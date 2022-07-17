package com.example.mynotesapp.model;

import android.media.Image;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Notes implements Parcelable {
    private static int counter;
    public static ArrayList<Notes> notesArrayList = new ArrayList<Notes>();
    int id;
    private String noteName;
    private LocalDateTime date;
    private String noteDescription;
    private Image image;
    private File file;


    public int getId() {
        return id;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }


    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public static ArrayList<Notes> getNotesArrayList() {
        return notesArrayList;
    }

    public Notes() {

    }

    public Notes(String name) {
        this.noteName = name;
    }

    {
        id = ++counter;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Notes(Parcel in) {
        id = in.readInt();
        noteName = in.readString();
        date = (LocalDateTime) in.readSerializable();
        noteDescription = in.readString();
    }

    public String getNoteName() {
        return noteName;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getDate() {
        date = LocalDateTime.now();
        return date;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getNoteName());
        parcel.writeSerializable(getDate());
        parcel.writeString(getNoteDescription());
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
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
