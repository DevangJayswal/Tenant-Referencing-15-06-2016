package com.example.devang.tenantreferencing.model;

import java.io.Serializable;

/**
 * Created by Devang on 6/15/2016.
 */
public class TenancyTerm implements Serializable {
    public static final String TAG = "TenancyTerm";
    private static final long serialVersionUID = -7406082437623008161L;

    private long id;
    private String name;

    public TenancyTerm(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
