package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class timer extends AppCompatActivity {

    DBHelper db;
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timer = findViewById(R.id.timer);
        db = new DBHelper(getApplicationContext());

        new CountDownTimer(30000, 1000) {
            public void onTick(long l) {
                timer.setText("Completed in: " + l / 1000 + " Seconds");
            }

            public void onFinish() {
                timer.setText("COMPLETE");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }.start();

        db.removeAll("FAVOURITE");
    }
}