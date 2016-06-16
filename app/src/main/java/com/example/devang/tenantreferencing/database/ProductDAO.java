package com.example.devang.tenantreferencing.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Devang on 6/15/2016.
 */
public class ProductDAO {

    public static final String TAG = "CountryDAO";
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;

    public ProductDAO(Context context) {
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


    public ArrayList<String> getProduct() {
        ArrayList<String> list = new ArrayList<String>();
        mDatabase = mDbHelper.getReadableDatabase();
        mDatabase.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM " + DbHelper.tableProduct + "";
            Cursor cursor = mDatabase.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(1);
                    list.add(name);
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
}
