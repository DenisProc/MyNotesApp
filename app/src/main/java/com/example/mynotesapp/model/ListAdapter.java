package com.example.mynotesapp.model;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynotesapp.R;
import com.example.mynotesapp.ui.NotesFragmentOne;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

    private Notes note;
    private ArrayList<Notes> notesArrayList;
    private NotesFragmentOne fragmentOne;

    public ListAdapter(ArrayList<Notes> notesArrayList) {
        this.notesArrayList = Notes.getNotesArrayList();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup view, int viewType) {
        View v = LayoutInflater.from(view.getContext())
                .inflate(R.layout.cardview_notes, view, false);

        return new Holder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setData(notesArrayList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return note.notesArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView noteName;
        private TextView noteDesc;
        private TextView noteDate;
        private CardView noteCard;

        public Holder(@NonNull View itemView) {
            super(itemView);


            noteName = itemView.findViewById(R.id.note_name);
            noteDesc = itemView.findViewById(R.id.note_desc);
            noteDate = itemView.findViewById(R.id.note_date);
            noteCard = itemView.findViewById(R.id.note_card);
            noteCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    fragmentOne.noteBuilder(notesArrayList.get(position)); // TODO не пойму как сделать так чтобы он из этого места открывал второй фрагмент для редактирования заметки.
                }
            });

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setData(Notes arrayList, int position) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            noteName.setText(notesArrayList.get(position).getNoteName());
            noteDesc.setText(notesArrayList.get(position).getNoteDescription());
            noteDate.setText(String.valueOf(dateTimeFormatter.format(notesArrayList.get(position).getDate())));
        }
    }
}
