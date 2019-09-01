package com.example.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.my.Home.HomeFragment;
import com.example.my.Izin.IzinFragment;
import com.example.my.Login.LoginActivity;
import com.example.my.Notif.NotifFragment;
import com.example.my.Profile.Profile_Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment home = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_content, home);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_perizinan:
                    IzinFragment izin = new IzinFragment();
                    FragmentTransaction fragmentTransaction_izin = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_izin.replace(R.id.frame_content, izin);
                    fragmentTransaction_izin.commit();
                    return true;
                case R.id.navigation_notifications:
                    NotifFragment notif = new NotifFragment();
                    FragmentTransaction fragmentTransaction_notif = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_notif.replace(R.id.frame_content, notif);
                    fragmentTransaction_notif.commit();
                    return true;
            }
            return false;
        }
    };

    private Toolbar tool;
    SharedPref sharedPref;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment home = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, home);
        fragmentTransaction.commit();

        init();
        tool = findViewById(R.id.app_bar_home);
        setTitle(null);
        setSupportActionBar(tool);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.left_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile_item:
                Intent intent = new Intent(MainActivity.this, Profile_Activity.class);
                startActivity(intent);
                break;
            case R.id.logout_item:
                sharedPref.ClearAll();

                Intent in = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        sharedPref = new SharedPref(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
        }
    }

}
