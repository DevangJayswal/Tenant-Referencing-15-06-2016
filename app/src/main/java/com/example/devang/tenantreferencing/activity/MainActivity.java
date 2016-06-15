package com.example.devang.tenantreferencing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.devang.tenantreferencing.R;
import com.example.devang.tenantreferencing.database.AddEnquiryDAO;
import com.example.devang.tenantreferencing.database.DbHelper;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    //AddEnquiryDAO addEnquiryDAO;
    FloatingActionButton fabAddEnquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Creating database and table*/
        dbHelper = new DbHelper(this);

        //addEnquiryDAO = new AddEnquiryDAO(this);
        /*Creating database and table end */

        /* onclick FAB (+) , open AddEnquiry */
        onClickfabAddEnquiry();

    }

    public void onClickfabAddEnquiry() {
        fabAddEnquiry = (FloatingActionButton) findViewById(R.id.fabAddEnquiry);
        fabAddEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.devang.tenantreferencing.activity.AddEnquiryActivity");

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
