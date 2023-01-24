package com.example.activityresultlauncherexample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //declare variables
    private TextView tvResult;
    private Button btnGetData;
    private ActivityResultLauncher<Intent> getDataLaucher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize view components
        initComponents();

        //initialize the ActivityResultLaucher
        getDataLaucher = initGetDataLaucher();
        //trigger the click event
        btnGetData.setOnClickListener(getDataClickListener());
    }

    private void initComponents() {
        tvResult = findViewById(R.id.tvResult);
        btnGetData = findViewById(R.id.btnGetResult);
    }


    private ActivityResultLauncher<Intent> initGetDataLaucher() {
        ActivityResultCallback<ActivityResult> callback = getResultCallback();
        //method registerForActivityResult requires two parameters:
        //1.the type of action / data brought into the current activity
        //2.the callback which brings the actual needed result
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                callback);
    }

    //the callback is based on the method onActivityResult
    private ActivityResultCallback<ActivityResult> getResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //process the received data from SendDataActivity
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String stringResult = result.getData().getStringExtra(SendDataActivity.SEND_KEY);
                    if (stringResult != null) {
                        tvResult.setText(stringResult);
                    }
                }
            }
        };
    }

    //define the click listener associated with the click event
    private View.OnClickListener getDataClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize the intent used to open the second activity
                Intent intent = new Intent(getApplicationContext(), SendDataActivity.class);
                //actually lauching the second activity
                getDataLaucher.launch(intent);
            }
        };
    }
}