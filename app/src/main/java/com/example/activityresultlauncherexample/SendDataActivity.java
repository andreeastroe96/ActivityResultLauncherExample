package com.example.activityresultlauncherexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendDataActivity extends AppCompatActivity {
    public static final String SEND_KEY = "sendString";
    //define variables
    private EditText etData;
    private Button btnSendData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        //initialize View components
        initComponents();
        //get the intent object used to launch the activity
        intent = getIntent();
        //trigger the click event
        btnSendData.setOnClickListener(sendDataClickListener());
    }

    private void initComponents() {
        etData = findViewById(R.id.etDataToSend);
        btnSendData = findViewById(R.id.btnSendData);
    }

    private View.OnClickListener sendDataClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do a validation of the input
                if (isValid()) {
                    //get user input
                    String dataToBeSent = etData.getText().toString();
                    //attach the input to the intent
                    intent.putExtra(SEND_KEY, dataToBeSent);
                    //send the intent object to MainActivity
                    setResult(RESULT_OK, intent);
                    //destroy the second activity
                    finish();
                }
            }
        };
    }

    private boolean isValid() {
        if (etData.getText() == null || etData.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.errorMessageToast,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}