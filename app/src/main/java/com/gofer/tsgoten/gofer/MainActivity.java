package com.gofer.tsgoten.gofer;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    private TextView textViewTitle;
    private Typeface typeface_verdana;
    private ImageView imageButtonSubmit;
    static final String SUBMIT_TYPE="submit type";
    static final int POST_KEY_CODE = 000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.silver));

        textViewTitle = findViewById(R.id.id_main_TextView_title);
        listViewMain = findViewById(R.id.id_main_ListView);
        imageButtonSubmit = findViewById(R.id.id_main_ImageView_addButton);

        typeface_verdana = Typeface.createFromAsset(getAssets(), "verdana.ttf");
        textViewTitle.setTypeface(typeface_verdana);
        textViewTitle.setTextSize(80);
        textViewTitle.setTextColor(getResources().getColor(R.color.silver, null)); //light blue

        BottomNavigationView bottomNavigationView = findViewById(R.id.id_main_NavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_main_NavigationView_offer:
                        setViewOffer();
                        return true;
                    case R.id.id_main_NavigationView_task:
                        setViewTask();
                        return true;
                }
                return false;
            }
        });

        setViewOffer(); //Because android default selects the first item in the navigation view
    }
    public void setViewOffer(){
        textViewTitle.setText(R.string.title_offer);

        ArrayList<Service>services=new ArrayList<>();
        //need to populate this ArrayList
        //Remember to fill with Offers
        long unixTime = System.currentTimeMillis()/1000;
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", unixTime));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, services);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //when you click on an element of the view we change shit here!!
            }
        });

        imageButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra(SUBMIT_TYPE, "offer");
                startActivityForResult(intent, POST_KEY_CODE);
            }
        });
    }

    public void setViewTask(){
        textViewTitle.setText(R.string.title_task);

        ArrayList<Service>services=new ArrayList<>();
        //need to populate this ArrayList
        //Remember to fill with Tasks
        //services.add(new Task("ok", "description", "cost", null));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, services);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //when you click on an element of the view we change shit here!!
            }
        });

        imageButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra(SUBMIT_TYPE, "task");
                startActivityForResult(intent, POST_KEY_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == POST_KEY_CODE){
                String [] objArray = data.getStringArrayExtra(PostActivity.SERVICE_OBJ_KEY);
                Long unixTime = data.getLongExtra(PostActivity.SERVICE_TIME_KEY, 00);
                switch (objArray[0]){
                    case "offer":
                        Offer offer = new Offer(objArray[1], objArray[2], objArray[3], unixTime);
                        //Add this object to the database
                        break;

                    case "task":
                        Task task = new Task(objArray[1], objArray[2], objArray[3], unixTime);
                        //Add this object to the database
                        break;
                }
            }
        }
    }


}
