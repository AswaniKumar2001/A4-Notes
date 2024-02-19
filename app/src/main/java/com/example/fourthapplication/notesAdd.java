package com.example.fourthapplication;

import androidx.fragment.app.Fragment;

public class notesAdd extends Fragment {

    private String title, des;

    public notesAdd(String title, String des ) {
        this.title = title;
        this.des = des;

    }

    //getters and setters for all variables

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
