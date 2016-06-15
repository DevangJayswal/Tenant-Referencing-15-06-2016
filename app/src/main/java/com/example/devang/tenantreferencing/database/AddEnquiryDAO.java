package com.example.devang.tenantreferencing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.devang.tenantreferencing.model.Enquiry;

/**
 * Created by Devang on 6/8/2016.
 */
public class AddEnquiryDAO {

    //public static final String databaseName = "tenantReferencing.db";
    public static final String TAG = "AddEnquiryDAO";

    // Datbase fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.colEnquiryEnquiryID,
            DbHelper.colEnquiryNoOfTenants, DbHelper.colEnquiryAddress1,
            DbHelper.colEnquiryAddress2,
            DbHelper.colEnquiryTown, DbHelper.colEnquiryPostcode, DbHelper.colEnquiryCountry,
            DbHelper.colEnquiryTenancyStartDate, DbHelper.colEnquiryProduct,
            DbHelper.colEnquiryTenancyTerm};

    public AddEnquiryDAO(Context context) {
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

    public void close() {
        mDbHelper.close();
    }

    public Enquiry insertEnquiry(long noOfTenants, String address1, String address2,
                                       String town, String postcode, long country, String tenancyStartDate, long product, long tenancyTerm) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.colEnquiryNoOfTenants, noOfTenants);
        values.put(DbHelper.colEnquiryAddress1, address1);
        values.put(DbHelper.colEnquiryAddress2, address2);
        values.put(DbHelper.colEnquiryTown, town);
        values.put(DbHelper.colEnquiryPostcode, postcode);
        values.put(DbHelper.colEnquiryCountry, country);
        values.put(DbHelper.colEnquiryTenancyStartDate, tenancyStartDate);
        values.put(DbHelper.colEnquiryProduct, product);
        values.put(DbHelper.colEnquiryTenancyTerm, tenancyTerm);

        long insertId = mDatabase
                .insert(DbHelper.tableEnquiry, null, values);
        Cursor cursor = mDatabase.query(DbHelper.tableEnquiry, mAllColumns,
                DbHelper.colEnquiryEnquiryID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        //Enquiry newEnquiry = cursorToCompany(cursor);
        Enquiry newEnquiry = cursorToEnquiry(cursor);
        cursor.close();
        return newEnquiry;
    }


//    public AddEnquiryDAO(Context context) {
//        super(context, databaseName, null, 1);
////        SQLiteDatabase db = this.getWritableDatabase();
//    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE Enquiry (enquiryID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " noOfTenants INTEGER, address1 TEXT, address2 TEXT, town TEXT, postcode TEXT, country INTEGER," +
//                "tenancyStartDate date, product INTEGER, tenancyTerm INTEGER)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS Enquiry");
//        onCreate(db);
//    }

//    public static final String tableName = "AddEnquiryDAO";
//
//    public static final String col1 = "noOfTenants";
//    public static final String col2 = "address1";
//    public static final String col3 = "address2";
//    public static final String col4 = "town";
//    public static final int col5 = 1; //country
//    public static final String col6 = "tenancyStartDate";
//    public static final int col7 = 1 ;//product
//    public static final int col8 = 1; //tenancyTerm


//    public boolean insertData(int noOfTenants, String address1, String address2,
//                              String town, String postcode, int country, String tenancyStartDate, int product, int tenancyTerm) {
//        // going to create database and table
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("noOfTenants", noOfTenants);
//        contentValues.put("address1", address1);
//        contentValues.put("address2", address2);
//        contentValues.put("town", town);
//        contentValues.put("postcode", postcode);
//        contentValues.put("country", country);
//        contentValues.put("tenancyStartDate", tenancyStartDate);
//        contentValues.put("product", product);
//        contentValues.put("tenancyTerm", tenancyTerm);
//
//        long result = db.insert("Enquiry", null, contentValues);
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }

    protected Enquiry cursorToEnquiry(Cursor cursor) {
        Enquiry company = new Enquiry();
        company.setmId(cursor.getLong(0));
        company.setmNoOfTenants(cursor.getLong(1));
        company.setmAddress1(cursor.getString(2));
        company.setmAddress2(cursor.getString(3));
        company.setmTown(cursor.getString(4));
        company.setmPostcode(cursor.getString(5));
        company.setmCountry(cursor.getLong(6));
        company.setmTenancyStartDate(cursor.getString(7));
        company.setmProduct(cursor.getLong(8));
        company.setmTenancyTerm(cursor.getLong(9));

        return company;
    }

}
