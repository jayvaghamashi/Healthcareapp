package com.example.healthcare1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthcare1.db";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context, String healthcare1, Object o, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, email TEXT, password TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
        String createTableQuery1 = "CREATE TABLE cart ( username TEXT, product TEXT, price float,otype text)";
        sqLiteDatabase.execSQL(createTableQuery1);

        String createTableQuery2 = "CREATE TABLE orderplace ( username TEXT, fullname TEXT, address text, number text,pincode int, date text, time text, amount float,otype text)";
        sqLiteDatabase.execSQL(createTableQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void updatePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", password);

        String[] whereArgs = { username };

        db.update("users", values, "username = ?", whereArgs);
        db.close();
    }

    public void register(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", password);
        db.insert("users", null, values);
        db.close();
    }

    public ArrayList<String> getUserData(int id) {
        ArrayList<String> userData = new ArrayList<>();

        if (id > 0) { // Check if id is valid
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM users WHERE id = ?";

            try (Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)})) {
                if (cursor.moveToFirst()) {
                    do {
                        // Adjust the number of columns retrieved and added to the ArrayList as needed
                        String data = cursor.getString(1) + "$" + cursor.getString(2) + "$" + cursor.getString(3) + "$" + cursor.getString(4);
                        userData.add(data);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                // Handle exceptions, log errors, or return an empty list if needed
                e.printStackTrace();
            } finally {
                db.close();
            }
        } else {
            // Handle the case where id is not valid (e.g., log an error, return an empty list, etc.)
        }

        return userData;
    }






    public void addcart(String username, String product, float price, String otype) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("product", product);
        values.put("price", price);
        values.put("otype",otype);
        db.insert("cart", null, values);
        db.close();
    }
    public void addOrder(String username, String fullname,  String address, int pincode, String number, String date, String time,float amount, String otype) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("fullname", fullname);
        values.put("address", address);
        values.put("pincode",pincode);
        values.put("number", number);
        values.put("date", date);
        values.put("time",time);
        values.put("amount", amount);
        values.put("otype",otype);
        db.insert("orderplace", null, values);
        db.close();
    }
    public int checkCart(String username, String product) {
        int result =0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?",str) ;
        if (c.moveToFirst()){
            result =1;
        }
        db.close();
        return result;
    }
    public int checkAppointmentExists(String username, String fullname, String address, String number,String date,String time) {
        int result = 0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = number;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and number = ? and date = ? and time = ?",str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }
        public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr =new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;

        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?",str) ;
        if (c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product +"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }


 public ArrayList<String> getOrderData(String username) {
     ArrayList<String> orderData = new ArrayList<>();

     if (username != null) {
         SQLiteDatabase db = this.getReadableDatabase();
         String query = "SELECT * FROM orderplace WHERE username = ?";

         Cursor cursor = db.rawQuery(query, new String[]{username});

         if (cursor != null) {
             if (cursor.moveToFirst()) {
                 do {
                     // Adjust the number of columns retrieved and added to the ArrayList as needed

                     String data = cursor.getString(1) + "$" + cursor.getString(2) + "$" + cursor.getString(3) + "$" + cursor.getString(4) + "$" + cursor.getString(5) + "$" + cursor.getString(6) + "$" + cursor.getString(7)+"$"+cursor.getString(8);
                     orderData.add(data);
                 } while (cursor.moveToNext());
             }
             cursor.close();
         }

         db.close();
     } else {
         // Handle the case where username is null (e.g., log an error, return an empty list, etc.)
     }

     return orderData;
 }


    public void removecart(String username, String otype) {

        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str );

        db.close();

    }

    public boolean login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        boolean loginSuccessful = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return loginSuccessful;
    }
}
