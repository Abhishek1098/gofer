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
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    private TextView textViewTitle;
    private FloatingActionButton floatingActionButton;
    private Typeface typeface_brainfish;

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
        floatingActionButton = findViewById(R.id.id_main_FloatingActionButton);

        typeface_brainfish = Typeface.createFromAsset(getAssets(), "verdana.ttf");
        textViewTitle.setTypeface(typeface_brainfish);
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
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));
        services.add(new Offer("WILL PAINT", "I will paint anything you want me to", "$10", "6;00"));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, services);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //when you click on an element of the view we change shit here!!
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    public void setViewTask(){
        textViewTitle.setText(R.string.title_task);

        ArrayList<Service>services=new ArrayList<>();
        //need to populate this ArrayList
        //Remember to fill with Tasks
        services.add(new Task("ok", "description", "cost", "time"));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, services);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //when you click on an element of the view we change shit here!!
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
