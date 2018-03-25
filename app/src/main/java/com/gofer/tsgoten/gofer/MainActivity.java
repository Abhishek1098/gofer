package com.gofer.tsgoten.gofer;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMain;
    static final String SUBMIT_TYPE="submit type";
    static final int POST_KEY_CODE = 000001;
    static final String ARRAY_KEY="key to access obj array in infoActivity";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("condition");

    ArrayList<Service>servicesOffer=new ArrayList<>();
    ArrayList<Service>servicesTask=new ArrayList<>();

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database;

    DatabaseReference offerRef;
    DatabaseReference tasksRef;
    @Override
    protected void onStart() {
        super.onStart();
        offerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    servicesOffer.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String title = (String) messageSnapshot.child("title").getValue();
                    String description = (String) messageSnapshot.child("description").getValue();
                    long time = (long)messageSnapshot.child("time").getValue();
                    String cost = messageSnapshot.child("cost").getValue(String.class);
                    servicesOffer.add(new Offer(title, description, cost, time));
                    listViewMain.deferNotifyDataSetChanged();
                    setViewOffer();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        tasksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                servicesTask.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String title = (String) messageSnapshot.child("title").getValue();
                    String description = (String) messageSnapshot.child("description").getValue();
                    long time = (long)messageSnapshot.child("time").getValue();
                    String cost = messageSnapshot.child("cost").getValue(String.class);
                    servicesTask.add(new Offer(title, description, cost, time));
                    listViewMain.deferNotifyDataSetChanged();
                    setViewTask();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent (this, SignupActivity.class);
        startActivity(intent);

        firebaseUser = mAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        offerRef = database.getReference("offers");
        tasksRef = database.getReference("tasks");

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.americanBlue));

        listViewMain = findViewById(R.id.id_main_ListView);
        //imageButtonSubmit = findViewById(R.id.id_main_ImageView_addButton);

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
                    case R.id.id_main_NavigationView_addButton:
                        Intent intent1 = new Intent(MainActivity.this, PostActivity.class);
                        startActivityForResult(intent1, POST_KEY_CODE);

                }
                return false;
            }
        });

        setViewOffer(); //Because android default selects the first item in the navigation view
    }
    public void setViewOffer(){
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, servicesOffer);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                String [] objArray = new String[5];
                objArray[0]="offer";
                objArray[1]=servicesOffer.get(position).getTitle();
                objArray[2]=servicesOffer.get(position).getCost();
                objArray[3]=servicesOffer.get(position).getDescription();
                objArray[4]=servicesOffer.get(position).timeToString();
                intent.putExtra(ARRAY_KEY, objArray);
                startActivity(intent);
            }
        });

    }

    public void setViewTask(){
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, servicesTask);
        listViewMain.setAdapter(customAdapter);
        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                String [] objArray = new String[5];
                objArray[0]="offer";
                objArray[1]=servicesTask.get(position).getTitle();
                objArray[2]=servicesTask.get(position).getCost();
                objArray[3]=servicesTask.get(position).getDescription();
                objArray[4]=servicesTask.get(position).timeToString();
                intent.putExtra(ARRAY_KEY, objArray);
                startActivity(intent);
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
                        Offer offer = new Offer(objArray[1], objArray[3],  objArray[2],unixTime);
                        //Add this object to the database
                        offerRef.child(offerRef.push().getKey()).setValue(offer);
                        break;

                    case "task":
                        Task task = new Task(objArray[1], objArray[3], objArray[2], unixTime);
                        //Add this object to the database
                        tasksRef.child(tasksRef.push().getKey()).setValue(task);
                        break;
                }
            }
        }
    }


}
