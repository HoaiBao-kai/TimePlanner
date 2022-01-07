package com.example.n15_timeplanner;

import java.io.Serializable;

public class Notetitle implements Serializable {
    String id;
    String title;
    String noteSub;
    String date;
    topic sptopic;
    String colornote;
    public Notetitle() {

    }

    public Notetitle(String id, String title, String noteSub, String date, topic sptopic, String colornote) {
        this.id = id;
        this.title = title;
        this.noteSub = noteSub;
        this.date = date;
        this.sptopic = sptopic;
        this.colornote = colornote;
    }

    public String getColornote() {
        return colornote;
    }

    public void setColornote(String colornote) {
        this.colornote = colornote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteSub() {
        return noteSub;
    }

    public void setNoteSub(String noteSub) {
        this.noteSub = noteSub;
    }

    public topic getSptopic() {
        return sptopic;
    }

    public void setSptopic(topic sptopic) {
        this.sptopic = sptopic;
    }
}
