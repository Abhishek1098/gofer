package com.gofer.tsgoten.gofer;


public class Service{
    private String title, description, cost;
    private Long time;
    public Service(String title, String description, String cost, Long time){
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
    public Long getTime(){
        return time;
    }
}
