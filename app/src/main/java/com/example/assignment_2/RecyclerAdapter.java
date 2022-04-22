package com.example.assignment_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Product> products;
    private Context context;
    private DBHelper db;

    public RecyclerAdapter(String tableName, Context context) {
        db = new DBHelper(context);
        this.products = db.getRecords(tableName);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice() + "$");
        int imageResource = context.getResources().getIdentifier(product.getImageFile(), null, context.getPackageName());
        holder.image.setImageDrawable(context.getResources().getDrawable(imageResource));
        if (db.getProduct("FAVOURITE", product.getName()) != null) {
            holder.favNum = 1;
            int imageResource2 = context.getResources().getIdentifier("@android:drawable/btn_star_big_on", null, context.getPackageName());
            holder.fav.setImageDrawable(context.getResources().getDrawable(imageResource2));
        }
        else
            holder.favNum = 0;
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.favNum == 0) {
                    db.addProduct("FAVOURITE", product);
                    int imageResource = context.getResources().getIdentifier("@android:drawable/btn_star_big_on", null, context.getPackageName());
                    holder.fav.setImageDrawable(context.getResources().getDrawable(imageResource));
                    holder.favNum = 1;
                }
                else {
                    db.remove("FAVOURITE", product);
                    int imageResource = context.getResources().getIdentifier("@android:drawable/btn_star_big_off", null, context.getPackageName());
                    holder.fav.setImageDrawable(context.getResources().getDrawable(imageResource));
                    holder.favNum = 0;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView price;
        ImageView image;
        ImageView fav;
        int favNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            fav = itemView.findViewById(R.id.favouriteButton2);
            fav.setClickable(true);
        }
    }
}
