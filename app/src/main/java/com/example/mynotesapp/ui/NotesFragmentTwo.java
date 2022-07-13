package com.example.mynotesapp.ui;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mynotesapp.R;

public class NotesFragmentTwo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_two, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

    }

    static final String ARG_INDEX = "index";

    public static NotesFragmentTwo newInstance(int index){
        NotesFragmentTwo fragment = new NotesFragmentTwo();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX,index);
        fragment.setArguments(args);
        return fragment;
    }
}