package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FavouriteProduct extends AppCompatActivity {

    private DBHelper db;
    RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private Button back, checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_product);

        recyclerView = findViewById(R.id.recyclerview);
        back = findViewById(R.id.backButton);
        checkout = findViewById(R.id.checkoutButton);
        db = new DBHelper(this);

        adapter = new RecyclerAdapter("FAVOURITE", this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.isTableEmpty("FAVOURITE")){
                    Toast.makeText(getApplicationContext(), "You need to add some item in the favourites", Toast.LENGTH_SHORT).show();
                }
                else{
                    switchActivity2();
                }
            }
        });
    }

    private void switchActivity() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivity2() {
        Intent switchActivityIntent = new Intent(this, Checkout_activity.class);
        startActivity(switchActivityIntent);
    }
}