package com.akash.applications.learncodeonline;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.akash.applications.learncodeonline.Constants.PreferenceKey;
import com.akash.applications.learncodeonline.SavePreferences.UserDetails;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        //startTimerThread();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                // Now change the color back. Needs to be done on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(new UserDetails(getBaseContext()).getUserName().equals(PreferenceKey.NO_NAME_FOUND))
                            startActivity(new Intent(getBaseContext(),PhoneRegisteration.class));
                        else
                            startActivity(new Intent(getBaseContext(),Home.class));

                        finish();
                    }
                });
            }
        }).start();

    }

}
