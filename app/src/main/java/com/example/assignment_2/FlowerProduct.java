package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlowerProduct extends AppCompatActivity {

    private DBHelper db;
    RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private Button link, back, favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_product);

        recyclerView = findViewById(R.id.recyclerview);

        back = findViewById(R.id.backButton);
        link = findViewById(R.id.linkButton);
        favourite = findViewById(R.id.favouriteButton);
        db = new DBHelper(this);
        db.removeAll("FLOWERS");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "https://www.consumerreports.org/home-garden/how-to-keep-flowers-fresh/#:~:text=Heat%20will%20hasten%20your%20flowers,fresh%20by%20avoiding%20direct%20sunlight.&text=As%20we%20said%2C%20bacteria%20are,every%20three%20days%2C%20Schleiter%20advises.";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities2();
            }
        });

        if (db.isTableEmpty("FLOWERS")) {
            insertData();
        }

        adapter = new RecyclerAdapter("FLOWERS", this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void insertData() {
        db.addProduct("FLOWERS", new Product("Red Roses", "Beautiful red roses for your loved ones",
                30.00, "@drawable/flower1"));
        db.addProduct("FLOWERS", new Product("Garden flowers", "Beautiful garden flowers",
                15.00, "@drawable/flower2"));
        db.addProduct("FLOWERS", new Product("Lilac", "Beautiful purple flowers",
                50.00, "@drawable/flower3"));
        db.addProduct("FLOWERS", new Product("Pink flowers", "Beautiful pink flowers",
                150.00, "@drawable/flower4"));
        db.addProduct("FLOWERS", new Product("Leucanthemum", "Amazing flowers for decoration",
                120.00, "@drawable/flower5"));
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