package com.example.mynotesapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynotesapp.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

    private ArrayList<Notes> arrayList;

    public ListAdapter(ArrayList<Notes> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup view, int viewType) {
        View v = LayoutInflater.from(view.getContext())
                .inflate(R.layout.cardview_notes, view, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }
}
