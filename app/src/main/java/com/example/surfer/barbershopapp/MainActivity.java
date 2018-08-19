package com.example.surfer.barbershopapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private MyPagerAdapter mViewPagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    selectedFragment = new HomeTabFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, selectedFragment).commit();

                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_buscar);
                    selectedFragment = new BuscarTabFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, selectedFragment).commit();

                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_usuario);
                    selectedFragment = new UsuarioTabFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, selectedFragment).commit();

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.rootLayout, new HomeTabFragment()).commit();

        /*ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);*/
    }

}
