package com.example.fourthapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notes extends Fragment {
    Button add;

    EditText title,description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        add = view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"Range", "MissingInflatedId"})
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MyContentProvider.title, title.getText().toString());
                values.put(MyContentProvider.description, description.getText().toString());

                getActivity().getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                // displaying a toast message
                Toast.makeText(view.getContext(), "New Record Inserted", Toast.LENGTH_LONG).show();

                replaceFragment(new One());

                title.setText("");
                description.setText("");
            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}