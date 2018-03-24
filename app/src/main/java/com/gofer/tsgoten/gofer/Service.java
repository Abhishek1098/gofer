package com.gofer.tsgoten.gofer;

import android.os.Parcelable;

public class Service{
    private String title, description, cost, time;
    public Service(String title, String description, String cost, String time){
        this.title=title;
        this.description=description;
        this.cost=cost;
        this.time=time;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getCost(){
        return cost;
    }
    public String getTime(){
        return time;
    }
}
