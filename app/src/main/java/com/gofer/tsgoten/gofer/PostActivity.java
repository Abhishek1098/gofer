package com.gofer.tsgoten.gofer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    static final String SERVICE_OBJ_KEY = "key for object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final Intent intentOld = getIntent();

        final EditText editTextTitle = findViewById(R.id.id_post_EditText_title);
        final EditText editTextCost = findViewById(R.id.id_post_EditText_cost);
        final EditText editTextDescription = findViewById(R.id.id_post_EditText_description);

        (findViewById(R.id.id_post_Button_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(editTextTitle.getText().toString().equals("")) && !(editTextCost.getText().toString().equals("")) && !(editTextDescription.getText().toString().equals(""))){

                    String [] objectArray = new String[5];
                    objectArray[0]=intentOld.getStringExtra(MainActivity.SUBMIT_TYPE);
                    objectArray[1]=editTextTitle.getText().toString();
                    objectArray[2]=editTextCost.getText().toString();
                    objectArray[3]=editTextDescription.getText().toString();
                    objectArray[4]="time";
                    //long unixTime = System.currentTimeMillis() / 1000L;

                    Intent intentNew = new Intent();
                    intentNew.putExtra(SERVICE_OBJ_KEY, objectArray);
                    setResult(RESULT_OK, intentNew);
                    finish();

                }else{
                    (Toast.makeText(PostActivity.this, "FILL OUT EVERYTHING", Toast.LENGTH_LONG)).show();
                }
            }
        });

    }
}
