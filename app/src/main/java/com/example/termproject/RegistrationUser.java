package com.example.termproject;

import android.content.Context;
import android.content.SharedPreferences;

public class RegistrationUser {

    private String reg_email,reg_pass,reg_security;
    Context context;
    SharedPreferences sharedPreferences;

   public RegistrationUser(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences("Registration_User",context.MODE_PRIVATE);
    }

    public String getReg_email() {
        reg_email=sharedPreferences.getString("reg_email","");
        return reg_email;
    }

    public void setReg_email(String reg_email) {
        this.reg_email = reg_email;
        sharedPreferences.edit().putString("reg_email",reg_email).apply();
    }

    public String getReg_pass() {
        reg_pass=sharedPreferences.getString("reg_pass","");
        return reg_pass;
    }

    public void setReg_pass(String reg_pass) {
        this.reg_pass = reg_pass;
        sharedPreferences.edit().putString("reg_pass",reg_pass).apply();
    }

    public String getReg_security() {
        reg_security=sharedPreferences.getString("reg_security","");
        return reg_security;
    }

    public void setReg_security(String reg_security) {
        this.reg_security = reg_security;
        sharedPreferences.edit().putString("reg_security",reg_security).apply();
    }



}
