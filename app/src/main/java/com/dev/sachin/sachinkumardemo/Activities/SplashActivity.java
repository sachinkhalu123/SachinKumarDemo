package com.dev.sachin.sachinkumardemo.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dev.sachin.sachinkumardemo.R;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT = 1500;
    public static final String PREF_CHECK_FILE_NAME= "loginCheck";
    boolean loginCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSharedPreferences(PREF_CHECK_FILE_NAME, MODE_PRIVATE).getBoolean("loggedin",false))
            loginCheck=true;
        else loginCheck=false;



        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inn;
                if (loginCheck)
                 inn=new Intent(SplashActivity.this, HomeActivity.class);
                else  inn=new Intent(SplashActivity.this, LoginActivity.class);

                inn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(inn);
                finish();
            }
        },TIME_OUT);

    }
}
