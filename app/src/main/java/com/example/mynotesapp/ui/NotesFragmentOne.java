package com.example.mynotesapp.ui;

import static com.example.mynotesapp.ui.NotesFragmentTwo.SELECTED_NOTE;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotesapp.R;
import com.example.mynotesapp.model.ListAdapter;
import com.example.mynotesapp.model.Notes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class NotesFragmentOne extends Fragment {
    Notes notes;
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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        ArrayList<Notes> list = Notes.getNotesArrayList();
        initRecyclerView(recyclerView, list);

        return view;

    }

    private void initRecyclerView(RecyclerView recyclerView, ArrayList<Notes> arrayList) {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ListAdapter listAdapter = new ListAdapter(arrayList);
        recyclerView.setAdapter(listAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            notes = (Notes) savedInstanceState.getParcelable(SELECTED_NOTE);
        }
/*        dataContainer = view.findViewById(R.id.fragment_one_container);
        initDisplay(dataContainer);

 */
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemDelete = menu.findItem(R.id.action_delete_note);
        if (itemDelete != null) {
            itemDelete.setVisible(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initDisplay() {
        initDisplay(dataContainer);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initDisplay(View view) {
        LinearLayout container = (LinearLayout) view;
        container.removeAllViews();
//        initList(container);
    }

    public void noteBuilder(Notes notes) {
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
/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initList(LinearLayout container) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < Notes.getNotesArrayList().size(); i++) {

            View itemView = getLayoutInflater().inflate(R.layout.cardview_notes, container, false);
            TextView textViewNoteName = itemView.findViewById(R.id.note_name);
            textViewNoteName.setText(Notes.getNotesArrayList().get(i).getNoteName());
            TextView textViewNoteDate = itemView.findViewById(R.id.note_date);
            textViewNoteDate.setText(String.valueOf(dateTimeFormatter.format(Notes.getNotesArrayList().get(i).getDate())));
            TextView textViewNoteDesc = itemView.findViewById(R.id.note_desc);
            textViewNoteDesc.setText(Notes.getNotesArrayList().get(i).getNoteDescription());


            final int index = i;
            itemView.setOnClickListener(cardViewClick ->
                    noteBuilder(Notes.getNotesArrayList().get(index)));
            container.addView(itemView);
            initPopupMenu(container, itemView, index);


        }
    }

 */

    private void initPopupMenu(LinearLayout layout, View itemView, int index) {
        itemView.setOnLongClickListener(view -> {
            Activity activity = requireActivity();
            PopupMenu popupMenu = new PopupMenu(activity, view);
            activity.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_popup_delete:
                            Notes.notesArrayList.remove(index);
                            initDisplay();
                            return true;
                        case R.id.action_popup_send:
                            Toast.makeText(activity, "Отправляем файл через WhatsApp", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return true;
                }

                ;
            });
            popupMenu.show();
            return true;
        });
    }


}