package com.example.finalapp;

public class Complaint {

    private String title,description;
    private int priority;

    public Complaint() {
        //empty constructor
    }

    public Complaint(String title, String description,int priority){

        this.title=title;
        this.description=description;
        this.priority=priority;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
