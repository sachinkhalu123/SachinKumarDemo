package com.dev.sachin.sachinkumardemo.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.sachin.sachinkumardemo.HelperClasses.GitApiClient;
import com.dev.sachin.sachinkumardemo.HelperClasses.GitApiInterface;
import com.dev.sachin.sachinkumardemo.HelperClasses.UserProfile;
import com.dev.sachin.sachinkumardemo.R;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    private UserProfile profDetails;
    GitApiInterface apiInterface;
    SharedPreferences mPrefs;
    public static final String PREF_FILE_NAME = "userPref";
    @SuppressWarnings("deprecation")
    ProgressDialog progressBar;
    LinearLayout mainLayout;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainLayout= (LinearLayout) findViewById(R.id.main_layout);
        usernameEditText= (EditText) findViewById(R.id.et_username);
        passwordEditText= (EditText) findViewById(R.id.et_password);
        loginButton= (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        apiInterface= GitApiClient.getApiClient().create(GitApiInterface.class);
        mPrefs = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_login){
            String uName=usernameEditText.getText().toString();
            String pass=passwordEditText.getText().toString();
            if (uName.equals("") || pass.equals(""))
                return;
            String authToken=getAuthToken(uName,pass);
            Log.d("authtoken=="," "+authToken);
            Call<UserProfile> profileCall=apiInterface.getProfile(authToken);


            progressBar=new ProgressDialog(this);
            progressBar.setTitle("Please wait...");
            progressBar.show();



            profileCall.enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                    progressBar.dismiss();

                    if (response.code()==200) {
                        Log.d("resp==", " " + response);
                        profDetails = response.body();
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(profDetails);
                        prefsEditor.putString("userDetails", json);
                        prefsEditor.commit();

                        Intent in=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(in);
                        finish();
                    }else if (response.code()==401){
                        Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }else if (response.code()==403){
                        Toast.makeText(LoginActivity.this, "Forbidden", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    progressBar.dismiss();
                    t.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Unable to login. Please try again", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public static String getAuthToken(String uname,String pass) {
        byte[] data = new byte[0];
        try {
            data = (uname + ":" + pass).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }
}
