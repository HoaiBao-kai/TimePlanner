package com.example.n15_timeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContent, new Fragment_danhmuc());
        fragmentTransaction.commit();

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DrawerLayout drawer=findViewById(R.id.drawer_layout);
                displayView(item);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }


        });
    }

    public void onBackPressed(){
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    public void displayView(MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_calendar:
                Intent intent = new Intent(MainActivity.this,CalenderActivity.class);
                startActivity(intent);
                break;
//
//            case R.id.navigation_notification:
//                fragment=new DashboardFragment();
//                item.setChecked(true);
//                break;
        }
        if(fragment!=null){
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent,fragment);
            fragmentTransaction.commit();
        }
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.add, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.addstaff:
//                Intent i = new Intent(this, addstaff.class);
//                startActivity(i);
//                break;
//            case R.id.adddepartment:
//                Intent intent=new Intent(this,addDepartment.class);
//                startActivity(intent);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

}