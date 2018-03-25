package com.gofer.tsgoten.gofer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class InfoActivity extends AppCompatActivity {

    Button acceptReject;

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
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.americanBlue));

        TextView textViewTitle = findViewById(R.id.id_info_TextView_title);
        TextView textViewCost = findViewById(R.id.id_info_TextView_cost);
        TextView textViewTime = findViewById(R.id.id_info_TextView_timeUploaded);
        TextView textViewDescription = findViewById(R.id.id_info_TextView_description);
        acceptReject = findViewById(R.id.info_acceptReject_button);
        acceptReject.setText("Accept " + objArray[0]);
        acceptReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textViewTitle.setTextSize(25);
        textViewCost.setTextSize(15);
        textViewDescription.setTextSize(15);
        textViewTime.setTextSize(10);

        textViewTitle.setTypeface(typeface_verdana);
        textViewCost.setTypeface(typeface_verdana);
        textViewDescription.setTypeface(typeface_verdana);
        textViewTime.setTypeface(typeface_verdana);

        textViewTitle.setTextColor(getResources().getColor(R.color.americanBlue, null));
        textViewCost.setTextColor(getResources().getColor(R.color.americanBlue, null));
        textViewDescription.setTextColor(getResources().getColor(R.color.americanBlue, null));
        textViewTime.setTextColor(getResources().getColor(R.color.americanBlue, null));

        textViewTitle.setText("  "+objArray[1]);
        textViewCost.setText("  "+objArray[2]);
        textViewTime.setText("  "+objArray[4]);
        textViewDescription.setText("  "+objArray[3]);
    }
}
