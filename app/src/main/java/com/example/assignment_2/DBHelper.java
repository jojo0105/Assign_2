package com.example.assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "assign_2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE GIFTS (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, " +
                "description VARCHAR, price DOUBLE, imagefile VARCHAR)";
        String query2 = "CREATE TABLE FLOWERS (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, " +
                "description VARCHAR, price DOUBLE, imagefile VARCHAR)";
        String query3 = "CREATE TABLE FAVOURITE (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, " +
                "description VARCHAR, price DOUBLE, imagefile VARCHAR)";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean isTableEmpty(String tableName) {
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToNext())
            return false;
        else
            return true;
    }

    public void addProduct(String tableName, Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", product.getName());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        values.put("imagefile", product.getImageFile());
        db.insert(tableName, null, values);

        db.close();
    }

    public ArrayList<Product> getRecords(String tableName) {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();

        if(isTableEmpty(tableName)){
            return null;
        }

        Cursor c = db.rawQuery(query, null);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String name = c.getString(1);
            String description = c.getString(2);
            Double price = c.getDouble(3);
            String imageFile = c.getString(4);
            products.add(new Product(name, description, price, imageFile));
        }

        c.close();
        db.close();

        return products;
    }

    public void remove(String tableName, Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, "name=?", new String[]{product.getName()});
        db.close();
    }

    public void removeAll(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    public Product getProduct(String tableName,String name) {
        String query = "SELECT * FROM " + tableName + " WHERE name = '" + name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (!cursor.moveToFirst()) {
            return null;
        }
        else {
            String itemName = cursor.getString(1);
            String description = cursor.getString(2);
            Double price = cursor.getDouble(3);
            String imageFile = cursor.getString(4);
            return new Product(itemName, description, price, imageFile);
        }
    }

}
