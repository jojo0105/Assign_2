package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Checkout_activity extends AppCompatActivity {

    Button checkout;
    EditText name, address;
    RadioButton pickUp, delivered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkout = findViewById(R.id.checkout);
        name = findViewById(R.id.nameET);
        address = findViewById(R.id.addressET);
        pickUp = findViewById(R.id.pichUpRB);
        delivered = findViewById(R.id.deliverRB);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pickUp.isChecked() || delivered.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), timer.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please fill out every section", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}