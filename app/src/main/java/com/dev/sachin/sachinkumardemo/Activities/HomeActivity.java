package com.dev.sachin.sachinkumardemo.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.dev.sachin.sachinkumardemo.HelperClasses.UserDetailsPrefs;
import com.dev.sachin.sachinkumardemo.R;
import com.dev.sachin.sachinkumardemo.fragments.HomeFragment;
import com.dev.sachin.sachinkumardemo.fragments.ProfileFragment;

import static com.dev.sachin.sachinkumardemo.Activities.LoginActivity.PREF_FILE_NAME;


public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public static final String PREF_CHECK_FILE_NAME= "loginCheck";
    SharedPreferences checkPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        checkPrefs = getSharedPreferences(PREF_CHECK_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = checkPrefs.edit();
        prefsEditor.putBoolean("loggedin", true);
        prefsEditor.commit();



        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.nav_header_name);
        ImageView nav_img = (ImageView) hView.findViewById(R.id.nav_header_imgview);
        nav_user.setText(UserDetailsPrefs.getuserDetails(this).getLogin());
        Glide.with(this).load(UserDetailsPrefs.getuserDetails(this).getAvatarImg()).into(nav_img);
        FragmentTransaction ftHome = getSupportFragmentManager().beginTransaction();
        ftHome.add(R.id.container, new HomeFragment());
        ftHome.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
         loadSelectedScreen(item.getItemId());
            return false;
    }


    public void loadSelectedScreen(int itemId){
        Fragment fragment=null;

        switch (itemId){
            case R.id.nav_home:
                FragmentTransaction ftHome = getSupportFragmentManager().beginTransaction();
                if (getSupportFragmentManager().findFragmentById(R.id.container)!=null)
                    ftHome.replace(R.id.container, new HomeFragment());
                else  ftHome.add(R.id.container, new HomeFragment());
                ftHome.commit();
                break;

            case R.id.nav_profile:
                FragmentTransaction ftProfile = getSupportFragmentManager().beginTransaction();
                if (getSupportFragmentManager().findFragmentById(R.id.container)!=null)
                    ftProfile.replace(R.id.container, new ProfileFragment());
                else  ftProfile.add(R.id.container, new ProfileFragment());
                ftProfile.commit();
                break;

            case R.id.nav_logout:
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                SharedPreferences settings = getSharedPreferences(PREF_CHECK_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences settings2 = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
                settings.edit().clear().apply();
                settings2.edit().clear().apply();
                finish();
        }
    }

        public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
