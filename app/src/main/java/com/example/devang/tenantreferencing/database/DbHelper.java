package com.example.devang.tenantreferencing.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Devang on 6/8/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";
    public static final String databaseName = "tenantReferencing.db";
    private static final int databaseVersion = 1;

    // Enquiry table
    public static final String tableEnquiry = "Enquiry";
    public static final String colEnquiryEnquiryID = "enquiryID";
    public static final String colEnquiryNoOfTenants = "noOfTenants";
    public static final String colEnquiryAddress1 = "address1";
    public static final String colEnquiryAddress2 = "address2";
    public static final String colEnquiryTown = "town";
    public static final String colEnquiryPostcode = "postcode";
    public static final String colEnquiryCountry = "country";
    public static final String colEnquiryTenancyStartDate = "tenancyStartDate";
    public static final String colEnquiryProduct = "product";
    public static final String colEnquiryTenancyTerm = "tenancyTerm";

    // Country table
    public static final String tableCountry = "Country";
    public static final String colCountryCountryID = "countryID";
    public static final String colCountryName = "name";

    // Product table
    public static final String tableProduct = "Product";
    public static final String colProductProductID = "productID";
    public static final String colProductName = "name";

    //TenancyTerm Table
    public static final String tableTenancyTerm = "TenancyTerm";
    public static final String colTenancyTermTenancyTermID = "tenancyTermID";
    public static final String colTenancyTermName = "name";

    //SQL statement for creating ENQUIRY table
    public static final String sqlCreateTableEnquiry = "CREATE TABLE " +
            tableEnquiry + " (" +
            colEnquiryEnquiryID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + colEnquiryNoOfTenants + " INTEGER, " +
            "" + colEnquiryAddress1 + " TEXT, " +
            "" + colEnquiryAddress2 + " TEXT, " +
            "" + colEnquiryTown + " TEXT, " +
            "" + colEnquiryPostcode + " TEXT, " +
            "" + colEnquiryCountry + " INTEGER," +
            "" + colEnquiryTenancyStartDate + " date, " +
            "" + colEnquiryProduct + " INTEGER, " +
            "" + colEnquiryTenancyTerm + " INTEGER)";

    //SQL statement for creating Country table
    public static final String sqlCreateTableCountry = "CREATE TABLE " +
            tableCountry + " (" +
            colCountryCountryID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + colCountryName + " TEXT)";

    //SQL statement for creating Product table
    public static final String sqlCreateTableProduct = "CREATE TABLE " +
            tableProduct + " (" +
            colProductProductID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + colProductName + " TEXT)";

    //SQL statement for creating TenancyTerm table
    public static final String sqlCreateTableTenancyTerm = "CREATE TABLE " +
            tableTenancyTerm + " (" +
            colTenancyTermTenancyTermID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + colTenancyTermName + " TEXT)";



    public DbHelper(Context context) {
        super(context, databaseName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //executing create statement

        /* Creating Tables */
        Log.d(TAG,"DB Created");
        /* Creating Table */
        db.execSQL(sqlCreateTableEnquiry);
        db.execSQL(sqlCreateTableCountry);
        db.execSQL(sqlCreateTableProduct);
        db.execSQL(sqlCreateTableTenancyTerm);
        /* Creating Table end */

        /* Country Insertion */
        String country[] = {"India", "Pakistan", "Canada", "USA", "Cyprus", "UK","China"};
        for (int i = 0; i < country.length; ++i) {
            db.execSQL("INSERT INTO " + tableCountry + " (name) values ('" + country[i] + "')");
        }
        /* Country Insertion end */

        /* Product Insertion */
        String product[] = {"Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"};
        for (int i = 0; i < product.length; ++i) {
            db.execSQL("INSERT INTO " + tableProduct + " (name) values ('" + product[i] + "')");
        }
        /* Product Insertion end */

        /* TenancyTerm Insertion */
        String tenancyTerm[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < tenancyTerm.length; ++i) {
            db.execSQL("INSERT INTO " + tableTenancyTerm + " (name) values ('" + tenancyTerm[i] + "')");
        }

        /* TenancyTerm Insertion end */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + tableEnquiry);
        db.execSQL("DROP TABLE IF EXISTS " + tableCountry);
        db.execSQL("DROP TABLE IF EXISTS " + tableProduct);
        db.execSQL("DROP TABLE IF EXISTS " + tableTenancyTerm);
        // recreate the tables
        onCreate(db);
    }




}
