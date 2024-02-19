package com.example.fourthapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class One extends Fragment implements Interface_Recycler {

    Button n1;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);


        n1  = view.findViewById(R.id.newNote);

        RecyclerView recyclerView;
        ArrayList<notesAdd> note = new ArrayList<>();
        RecyclerAdapter_Notes adapter = new RecyclerAdapter_Notes(getActivity(),note,this);

        recyclerView= view.findViewById(R.id.recycler);

        Cursor cursor = getActivity().getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
        // iteration of the cursor
        // to print whole table
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String title, des;
                while (!cursor.isAfterLast()) {
                    title = cursor.getString(cursor.getColumnIndex(MyContentProvider.title));
                    des = cursor.getString(cursor.getColumnIndex(MyContentProvider.description));
                    note.add(new notesAdd(title,des));
                    cursor.moveToNext();
                }
                //setting adapter with recyclerView
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Notes());
            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout,fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(notesAdd note, int position) {

    }
}