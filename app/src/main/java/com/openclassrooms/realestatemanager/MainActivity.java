package com.openclassrooms.realestatemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.openclassrooms.realestatemanager.Fragment.FilterFragment;
import com.openclassrooms.realestatemanager.Fragment.GoogleMapFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton addBtn;
    private ImageView editBtn;
    private ImageView filterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureFilter();



    }

    public Bundle getBundle() {
        Bundle bundle = getIntent().getExtras();
        return bundle;
    }

    private void configureFilter() {
        filterBtn = findViewById(R.id.filter_btn);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FilterFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .addToBackStack("filter")
                        .commit();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.drawer_maps:
                openMap();
                this.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.drawer_settings:
                break;
            case R.id.drawer_loan:
                openLoan();
                return true;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openMap() {
        Bundle bundle = new Bundle();
        Fragment fragment = new GoogleMapFragment();
        fragment.setArguments(bundle);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack("map")
                .commit();
    }

    private void openLoan() {
        Intent intent = new Intent(this, LoanSimulatorActivity.class);
        navigationView.getContext().startActivity(intent);
    }

    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.main_activity_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.main_activity_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
        super.onBackPressed();
    }



}
