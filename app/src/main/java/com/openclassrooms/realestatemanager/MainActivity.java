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

    // THE TWO LINES THAT WERE RELATED TO BUGS.

        // BEFORE : this.textViewMain = findViewById(R.id.activity_second_activity_text_view_main);

        // AFTER : this.textViewMain = findViewById(R.id.activity_main_activity_text_view_main);

        // BEFORE : int quantity = Utils.convertDollarToEuro(100);
        //          this.textViewQuantity.setTextSize(20);
        //          this.textViewQuantity.setText(quantity);

        // AFTER : this.textViewQuantity.setText(String.valueOf(quantity));


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
            case R.id.drawer_loan:
                openLoan();
                this.drawerLayout.closeDrawer(GravityCompat.START);
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
