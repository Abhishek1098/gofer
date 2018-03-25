package com.gofer.tsgoten.gofer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Typeface typeface_verdana = Typeface.createFromAsset(getAssets(), "verdana.ttf");

        Intent intent = getIntent();
        String [] objArray = intent.getStringArrayExtra(MainActivity.ARRAY_KEY);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.silver));

        TextView textViewTitle = findViewById(R.id.id_info_TextView_title);
        TextView textViewCost = findViewById(R.id.id_info_TextView_cost);
        TextView textViewTime = findViewById(R.id.id_info_TextView_timeUploaded);
        TextView textViewDescription = findViewById(R.id.id_info_TextView_description);

        textViewTitle.setTextSize(20);
        textViewCost.setTextSize(20);
        textViewDescription.setTextSize(20);
        textViewTime.setTextSize(20);

        textViewTitle.setTypeface(typeface_verdana);
        textViewCost.setTypeface(typeface_verdana);
        textViewDescription.setTypeface(typeface_verdana);
        textViewTime.setTypeface(typeface_verdana);

        textViewTitle.setText(objArray[1]);
        textViewCost.setText(objArray[2]);
        textViewTime.setText(objArray[4]);
        textViewDescription.setText(objArray[3]);
    }
}
