package com.example.administrator.iwillbe.mvc;

/**
 * Created by Administrator on 2016/12/12.
 */
public class PersonHomepage {
    String imagUrl;
    String userName;
    String time;
    String introText;
    String introimag[]={"http://127.0.0.1/Application/Home/Controller/mipmap/home-icon-seven.png"};
    String comment[];
    String reply[];

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public String[] getIntroimag() {
        return introimag;
    }

    public void setIntroimag(String[] introimag) {
        this.introimag = introimag;
    }

    public String[] getComment() {
        return comment;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public String[] getReply() {
        return reply;
    }

    public void setReply(String[] reply) {
        this.reply = reply;
    }
}
