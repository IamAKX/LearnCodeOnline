package com.akash.applications.learncodeonline.SavePreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.akash.applications.learncodeonline.Constants.PreferenceKey;

/**
 * Created by pratidhi on 14/6/18.
 */

public class UserDetails {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public UserDetails(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PreferenceKey.USER_DETAILS, Activity.MODE_PRIVATE);
    }

    // User Name
    public void setUserName(String name)
    {
        editor = preferences.edit();
        editor.putString(PreferenceKey.USER_NAME,name);
        editor.commit();
    }

    public String getUserName()
    {
        return preferences.getString(PreferenceKey.USER_NAME,PreferenceKey.NO_NAME_FOUND);
    }

    // User Email
    public void setUserEmail(String email)
    {
        editor = preferences.edit();
        editor.putString(PreferenceKey.USER_EMAIL,email);
        editor.commit();
    }

    public String getUserEmail()
    {
        return preferences.getString(PreferenceKey.USER_EMAIL,PreferenceKey.NO_EMAIL_FOUND);
    }

    // User Image
    public void setUserPhone(String phone)
    {
        editor = preferences.edit();
        editor.putString(PreferenceKey.USER_PHONE, phone);
        editor.commit();
    }

    public String getUserPhone()
    {
        return preferences.getString(PreferenceKey.USER_PHONE,PreferenceKey.NO_USER_PHONE);
    }

    public void logout()
    {
        editor = preferences.edit();
        editor.remove(PreferenceKey.USER_NAME);
        editor.remove(PreferenceKey.USER_EMAIL);
        editor.remove(PreferenceKey.USER_PHONE);
        editor.apply();
        editor.commit();
    }


}
