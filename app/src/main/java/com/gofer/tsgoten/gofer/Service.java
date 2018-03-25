package com.gofer.tsgoten.gofer;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Service{
    private String title, description, cost;

    private String firebaseKey;
    private float rating;

    private Long time;
    public Service(String title, String description, String cost, Long time){
        this.title=title;
        this.description=description;
        this.cost=cost;
        this.time=time;
        firebaseKey = "";
        rating = (float)Math.sqrt(Math.random()*25);
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
    public String timeToString(){
        Long dt = time;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis((dt)*1000);
        String day ="";
        switch (calendar.get(Calendar.DAY_OF_WEEK)-1){
            case 0: day = "Sunday"; break;
            case 1: day = "Monday"; break;
            case 2: day = "Tuesday"; break;
            case 3: day = "Wednesday"; break;
            case 4: day = "Thursday"; break;
            case 5: day = "Friday"; break;
            case 6: day = "Saturday"; break;
            case 7: day = "Sunday"; break;
        }
        String am_pm = "";
        switch (calendar.get(Calendar.AM_PM)){
            case 0:
                am_pm = "AM"; break;
            case 1:
                am_pm = "PM"; break;
        }

        String dateTime = day + " " + calendar.get(Calendar.HOUR) + " " + am_pm;
        return dateTime;
    }
    public String getFirebaseKey(){
        return firebaseKey;
    }
    public void setFirebaseKey(String s){
        firebaseKey = s;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
