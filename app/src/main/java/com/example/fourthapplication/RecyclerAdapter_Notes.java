package com.example.fourthapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter_Notes extends RecyclerView.Adapter<RecyclerAdapter_Notes.MyViewHolder> {

    Context context;
    //creating arraylist of note objects
    ArrayList<notesAdd> note;
    private Interface_Recycler Interface_Recycler;

    //creating constructor for adapter
    public RecyclerAdapter_Notes(Context context, ArrayList<notesAdd> book, Interface_Recycler Interface_Recycler ) {
        this.context = context;
        this.note = book;
        this.Interface_Recycler = Interface_Recycler;
    }

    @NonNull
    @Override
    //below method used to inflate the view when user scrolls up/down to show the details which coundn't fit the screen

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    @SuppressLint({"RecyclerView", "SimpleDateFormat", "SetTextI18n"})
    //onBindViewHolder method for setting details in view for all note titles one by one by using their position
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {

        //setting the details of the note to show
        holder.title.setText(note.get(position).getTitle());
        holder.description.setText(note.get(position).getDes());

        //below method is for setting on click response of the view

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interface_Recycler.onItemClick(note.get(position),position);
            }
        });

    }

    //getItemcount method return the size of the items to be displayed
    @Override
    public int getItemCount() {
        return note.size();
    }


    //MyViewHolder method for setting the view for recycle view
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //assigning the views by finding their respective id
            title = itemView.findViewById(R.id.CardTitle);
            description=itemView.findViewById(R.id.CardDescription);
            cardView=itemView.findViewById(R.id.Cardlayout);
        }
    }
}
