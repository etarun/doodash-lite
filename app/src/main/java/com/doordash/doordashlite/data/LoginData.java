package com.doordash.doordashlite.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 11/9/18.
 */

public class LoginData {

    private String email;

    private String password;

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

