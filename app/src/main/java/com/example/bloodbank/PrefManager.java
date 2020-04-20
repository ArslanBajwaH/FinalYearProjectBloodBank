package com.example.bloodbank;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "blood_bank";
    private static final String USER_NAME = "name";
    private static final  String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_CONTACT = "contact";
    private static final String USER_CITY = "city";
    private static final String USER_BLOOD = "blood";
    private static final String LOGGED_IN = "login";

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserName(String name){
        editor.putString(USER_NAME,name);
        editor.commit();
    }

    public String getUserName(){
        return sharedPreferences.getString(USER_NAME,null);
    }

    public void setUserEmail(String email){
        editor.putString(USER_EMAIL,email);
        editor.commit();
    }

    public String getUserEmail(){
        return sharedPreferences.getString(USER_EMAIL,null);
    }

    public void setUserPassword(String password){
        editor.putString(USER_PASSWORD,password);
        editor.commit();
    }

    public String getUserPassword(){
        return sharedPreferences.getString(USER_PASSWORD,null);
    }

    public void setUserContact(String contact){
        editor.putString(USER_CONTACT,contact);
        editor.commit();
    }

    public String getUserContact(){
        return sharedPreferences.getString(USER_CONTACT,null);
    }

    public void setUserCity(String city){
        editor.putString(USER_CITY,city);
        editor.commit();
    }

    public String getUserCity(){
        return sharedPreferences.getString(USER_CITY,null);
    }

    public void setUserBlood(String blood){
        editor.putString(USER_BLOOD,blood);
        editor.commit();
    }

    public String getUserBlood(){
        return sharedPreferences.getString(USER_BLOOD,null);
    }

    public void setLoggedIn(Boolean loggedIn){
        editor.putBoolean(LOGGED_IN,loggedIn);
        editor.commit();
    }

    public Boolean getLoggedin(){
        return sharedPreferences.getBoolean(LOGGED_IN,false);
    }
}
