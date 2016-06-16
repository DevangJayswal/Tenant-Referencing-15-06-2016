package com.example.devang.tenantreferencing.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.devang.tenantreferencing.R;
import com.example.devang.tenantreferencing.database.AddEnquiryDAO;
import com.example.devang.tenantreferencing.database.CountryDAO;
import com.example.devang.tenantreferencing.database.ProductDAO;
import com.example.devang.tenantreferencing.database.TenancyTermDAO;
import com.example.devang.tenantreferencing.model.Enquiry;

import java.util.ArrayList;
import java.util.Calendar;

public class AddEnquiryActivity extends AppCompatActivity {

    static final int dialogID = 0;
    int yearX, monthX, dayX;
    AddEnquiryDAO addEnquiryDAO;
    CountryDAO countryDAO;
    ProductDAO productDAO;
    TenancyTermDAO tenancyTermDAO;


    EditText editNumberOfTenants;
    EditText editAddress1;
    EditText editAddress2;
    EditText editTown;
    EditText editPostcode;
    Spinner spinnerCountry;
    Spinner spinnerProduct;
    Spinner spinnerTenancyTerm;

    Button btnDate;
    Button btnSubmit;
    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            yearX = year;
            monthX = monthOfYear + 1;
            dayX = dayOfMonth;
            Toast.makeText(AddEnquiryActivity.this, yearX + "/" + monthX + "/" + dayX, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Test", "Application Started");
        setContentView(R.layout.activity_add_enquiry);



        /* creating database instance */
        addEnquiryDAO = new AddEnquiryDAO(this);

        /* filling COUNTRY spinner from database*/
        countryDAO = new CountryDAO(this);
        ArrayList<String> listCountry = countryDAO.getCountry();
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listCountry);
        spinnerCountry.setAdapter(adapterCountry);
        /* filling COUNTRY spinner from database end*/

        /* filling PRODUCT spinner from database*/
        productDAO = new ProductDAO(this);
        ArrayList<String> listProduct = productDAO.getProduct();
        spinnerProduct = (Spinner) findViewById(R.id.spinnerProduct);
        ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listProduct);
        spinnerProduct.setAdapter(adapterProduct);
        /* filling PRODUCT spinner from database end*/

        /* filling TENANCYTERM spinner from database*/
        tenancyTermDAO = new TenancyTermDAO(this);
        ArrayList<String> listTenancyTerm = tenancyTermDAO.getTenancyTerm();
        spinnerTenancyTerm = (Spinner) findViewById(R.id.spinnerTenancyTerm);
        ArrayAdapter<String> adapterTenancyTerm = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listTenancyTerm);
        spinnerTenancyTerm.setAdapter(adapterTenancyTerm);
        /* filling TENANCYTERM spinner from database end*/



        /* creating database instance end */

        /* Date Picker */
        final Calendar calendar = Calendar.getInstance();
        yearX = calendar.get(Calendar.YEAR);
        monthX = (calendar.get(Calendar.MONTH) + 1);
        dayX = calendar.get(Calendar.DAY_OF_MONTH);
        showTenancyStartDateDialog();
        /* Date Picker Ends */


        /* Validation start */

        editNumberOfTenants = (EditText) findViewById(R.id.editTextNumberOfTenants);
        editNumberOfTenants.requestFocus();
        editNumberOfTenants.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editNumberOfTenants.getText().length() == 0)
                    editNumberOfTenants.setError("Enter No of Tenants");
            }
        });

        editAddress1 = (EditText) findViewById(R.id.editTextAddress1);
        editAddress1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editAddress1.getText().length() == 0)
                    editAddress1.setError("Enter address");
            }
        });

        editAddress2 = (EditText) findViewById(R.id.editTextAddress2);
        editAddress2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editAddress2.getText().length() == 0)
                    editAddress2.setError("Enter address");
            }
        });

        editTown = (EditText) findViewById(R.id.editTextTown);
        editTown.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editTown.getText().length() == 0)
                    editTown.setError("Enter town");
            }
        });

        editPostcode = (EditText) findViewById(R.id.editTextPostcode);
        editPostcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editPostcode.getText().length() == 0)
                    editPostcode.setError("Enter postcode");
            }
        });

        /* Validation end*/


        /* On SUBMIT, data insertion */
        addData();
        /* On SUBMIT, data insertion ends */

    }

    public void showTenancyStartDateDialog() {
        btnDate = (Button) findViewById(R.id.buttonTenancyStartDate);
        btnDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(dialogID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == dialogID)
            return new DatePickerDialog(this, dPickerListener, yearX, monthX, dayX);
        return null;
    }


    public boolean validation(String editNumberOfTenants, String editAddress1,
                              String editAddress2, String editTown, String editPostcode) {

        boolean isValidated = true;


        if (editNumberOfTenants.length() == 0) {
            isValidated = false;
            Toast.makeText(AddEnquiryActivity.this, "Enter no. of tenants !!", Toast.LENGTH_LONG).show();
        } else if (isValidated) {
            try {
                Long.parseLong(editNumberOfTenants);
            } catch (NumberFormatException e) {
                isValidated = false;
                Toast.makeText(AddEnquiryActivity.this, "No. of tenants must be numeric !!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else if (isValidated) {
            if (editAddress1.length() == 0) {
                isValidated = false;
                Toast.makeText(AddEnquiryActivity.this, "Enter address !!", Toast.LENGTH_LONG).show();
            }
        } else if (isValidated) {
            if (editAddress2.length() == 0) {
                isValidated = false;
                Toast.makeText(AddEnquiryActivity.this, "Enter address !!", Toast.LENGTH_LONG).show();
            }
        } else if (isValidated) {
            if (editTown.length() == 0) {
                isValidated = false;
                Toast.makeText(AddEnquiryActivity.this, "Enter Town !!", Toast.LENGTH_LONG).show();
            }
        } else if (isValidated) {
            if (editPostcode.length() == 0) {
                isValidated = false;
                Toast.makeText(AddEnquiryActivity.this, "Enter postcode !!", Toast.LENGTH_LONG).show();
            }
        }

        isValidated = false;
        return isValidated;
    }


    public void addData() {
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String noOfTenants = ((EditText) findViewById(R.id.editTextNumberOfTenants)).getText().toString();
                        String address1 = ((EditText) findViewById(R.id.editTextAddress1)).getText().toString();
                        String address2 = ((EditText) findViewById(R.id.editTextAddress2)).getText().toString();
                        String town = ((EditText) findViewById(R.id.editTextTown)).getText().toString();
                        String postcode = ((EditText) findViewById(R.id.editTextPostcode)).getText().toString();
                        boolean isValidated = validation(noOfTenants, address1, address2, town, postcode);
                        if (isValidated == false) {
                            return;
                        }

                        String date = yearX + "-" + monthX + "-" + dayX;
                        long tenants = 0;
                        try {
                            tenants = Long.parseLong(editNumberOfTenants.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(AddEnquiryActivity.this, "Only Numeric Allowed", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            return;
                        } finally {
                        }


                        Enquiry enquiry;
                        try {
                            enquiry = addEnquiryDAO.insertEnquiry(
                                    tenants,
                                    editAddress1.getText().toString(),
                                    editAddress2.getText().toString(),
                                    editTown.getText().toString(),
                                    editPostcode.getText().toString(),
                                    1,
                                    date,
                                    1,
                                    1
                            );
                            Toast.makeText(AddEnquiryActivity.this, "Data Inserted, id = " + enquiry.getmId() + "", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(AddEnquiryActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }
        );

    }
}


