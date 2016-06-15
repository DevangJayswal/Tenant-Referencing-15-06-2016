package com.example.devang.tenantreferencing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.devang.tenantreferencing.model.Country;

import java.util.ArrayList;


/**
 * Created by Devang on 6/13/2016.
 */
public class CountryDAO {

    public static final String TAG = "CountryDAO";
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.colCountryCountryID,
            DbHelper.colCountryName};


    public CountryDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DbHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public Country insertCountry(String countryName) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.colCountryName, countryName);
        long insertId = mDatabase
                .insert(DbHelper.tableCountry, null, values);
        Cursor cursor = mDatabase.query(DbHelper.tableCountry, mAllColumns,
                DbHelper.colCountryCountryID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Country newCountry = cursorToCountry(cursor);
        cursor.close();
        return newCountry;
    }

    public ArrayList<String> getCountry(){
        ArrayList<String> list  = new ArrayList<String>();
        mDatabase = mDbHelper.getReadableDatabase();
        mDatabase.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM "+ DbHelper.tableCountry +"";
            Cursor cursor = mDatabase.rawQuery(selectQuery, null);
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String countryName = cursor.getString(1);
                    list.add(countryName);
                }
            }
            mDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();
            mDatabase.close();
        }
        return list;
    }


    protected Country cursorToCountry(Cursor cursor) {
        Country country = new Country();
        country.setId(cursor.getLong(0));
        country.setName(cursor.getString(1));
        return country;
    }

}