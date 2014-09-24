package com.example.jks.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jks on 16.09.14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "fooddb";
    private static final String TABLE_FOOD = "food";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_SHOPPING_FLAG = "shoppingflag";
    private static final String KEY_ADDED_TIME = "addedtime";

    private static final String CREATE_DB = "CREATE TABLE " + TABLE_FOOD +
            "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_NAME + " TEXT," +
            KEY_QUANTITY + " INTEGER," +
            KEY_SHOPPING_FLAG + " TEXT, " +
            KEY_ADDED_TIME + " TEXT)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }
    public void insertFood(String name, int quantity,  String shoppingflag, String addedtime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ADDED_TIME,addedtime);
        values.put(KEY_SHOPPING_FLAG, shoppingflag);
        values.put(KEY_QUANTITY, quantity);
        values.put(KEY_NAME,name);
        db.insert(TABLE_FOOD, null, values);
        db.close();
      }
    public List<FoodData> getAllFoodData(){
        List<FoodData> foodDataList = new ArrayList<FoodData>();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " ORDER BY " + KEY_NAME + " COLLATE NOCASE";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if(cursor.moveToFirst()){
            do{
                FoodData foodData = new FoodData(cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4));
                foodDataList.add(foodData);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  foodDataList;
    }
    public List<String> getAllNames(){
        List<String> foodNameList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if(cursor.moveToFirst()){
            do{
                foodNameList.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  foodNameList;
    }
    public Cursor getAllRows(){
        String selectQuery = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    public List<FoodData> getAllShoppingNames(){
        List<FoodData> foodNameList = new ArrayList<FoodData>();

        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " ORDER BY " + KEY_NAME + " COLLATE NOCASE";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if(cursor.moveToFirst()){
            do if(cursor.getString(3).equals("True")) {
                FoodData foodData = new FoodData(cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4));
                foodNameList.add(foodData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return  foodNameList;
    }
    public void updateName(String newname, String oldname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, newname);
        String where = KEY_NAME + "=?";
        String[] value = {oldname};
        db.update(TABLE_FOOD, cv,where,value);
        db.close();
    }
    public void updateQuantitiy(String name, int newInt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_QUANTITY, newInt);
        String where = KEY_NAME + "=?";
        String [] value = {name};
        db.update(TABLE_FOOD, cv, where, value);
        db.close();
    }
    public void deleteItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_NAME + "=?";
        String [] arguements  = {name};
        db.delete(TABLE_FOOD,where, arguements );
        db.close();
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD,null,null);
    }

}