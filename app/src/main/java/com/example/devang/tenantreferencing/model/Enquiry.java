package com.example.devang.tenantreferencing.model;

import java.io.Serializable;

/**
 * Created by Devang on 6/14/2016.
 */
public class Enquiry implements Serializable {
    public static final String TAG = "Enquiry";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private long mNoOfTenants;
    private String mAddress1;
    private String mAddress2;
    private String mTown;
    private String mPostcode;
    private long mCountry;
    private String mTenancyStartDate;
    private long mProduct;
    private long mTenancyTerm;

    public Enquiry() {
    }

    public Enquiry(long mNoOfTenants, String mAddress1, String mAddress2, String mTown, String mPostcode, long mCountry, String mTenancyStartDate, long mProduct, long mTenancyTerm) {
        this.mNoOfTenants = mNoOfTenants;
        this.mAddress1 = mAddress1;
        this.mAddress2 = mAddress2;
        this.mTown = mTown;
        this.mPostcode = mPostcode;
        this.mCountry = mCountry;
        this.mTenancyStartDate = mTenancyStartDate;
        this.mProduct = mProduct;
        this.mTenancyTerm = mTenancyTerm;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public long getmNoOfTenants() {
        return mNoOfTenants;
    }

    public void setmNoOfTenants(long mNoOfTenants) {
        this.mNoOfTenants = mNoOfTenants;
    }

    public String getmAddress1() {
        return mAddress1;
    }

    public void setmAddress1(String mAddress1) {
        this.mAddress1 = mAddress1;
    }

    public String getmAddress2() {
        return mAddress2;
    }

    public void setmAddress2(String mAddress2) {
        this.mAddress2 = mAddress2;
    }

    public String getmTown() {
        return mTown;
    }

    public void setmTown(String mTown) {
        this.mTown = mTown;
    }

    public String getmPostcode() {
        return mPostcode;
    }

    public void setmPostcode(String mPostcode) {
        this.mPostcode = mPostcode;
    }

    public long getmCountry() {
        return mCountry;
    }

    public void setmCountry(long mCountry) {
        this.mCountry = mCountry;
    }

    public String getmTenancyStartDate() {
        return mTenancyStartDate;
    }

    public void setmTenancyStartDate(String mTenancyStartDate) {
        this.mTenancyStartDate = mTenancyStartDate;
    }

    public long getmProduct() {
        return mProduct;
    }

    public void setmProduct(long mProduct) {
        this.mProduct = mProduct;
    }

    public long getmTenancyTerm() {
        return mTenancyTerm;
    }

    public void setmTenancyTerm(long mTenancyTerm) {
        this.mTenancyTerm = mTenancyTerm;
    }
}
