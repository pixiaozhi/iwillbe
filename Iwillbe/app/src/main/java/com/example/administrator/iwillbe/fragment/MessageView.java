package com.example.administrator.iwillbe.fragment;

/**
 * Created by Administrator on 2016/12/13.
 */
public class MessageView {
    int image;
    String name;
    String context;
    int view_message = 1;

    public int getView_message() {
        return view_message;
    }

    public void setView_message(int view_message) {
        this.view_message = view_message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
