package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GiftProduct extends AppCompatActivity {

    private DBHelper db;
    RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private Button link, back, favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_product);

        recyclerView = findViewById(R.id.recyclerview);

        back = findViewById(R.id.backButton);
        link = findViewById(R.id.linkButton);
        favourite = findViewById(R.id.favouriteButton);
        db = new DBHelper(this);
        db.removeAll("GIFTS");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities2();
            }
        });

        if (db.isTableEmpty("GIFTS")) {
            insertData();
        }

        adapter = new RecyclerAdapter("GIFTS", this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void insertData() {
        db.addProduct("GIFTS", new Product("Walet", "Good wallet for your cards and change",
                30.00, "@drawable/gift1"));
        db.addProduct("GIFTS", new Product("LED lights", "Fancy lights that " +
                "changes colors based on a remote control to decorate any room",
                15.00, "@drawable/gift2"));
        db.addProduct("GIFTS", new Product("Ring", "Beautiful ring for men that is made out of pure silver",
                50.00, "@drawable/gift3"));
        db.addProduct("GIFTS", new Product("Speaker", "Wireless speaker that is waterproof",
                150.00, "@drawable/gift4"));
        db.addProduct("GIFTS", new Product("Shoes", "Confortable nike shoes",
                120.00, "@drawable/gift5"));
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivities2() {
        Intent switchActivityIntent = new Intent(this, FavouriteProduct.class);
        startActivity(switchActivityIntent);
    }
}