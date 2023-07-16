package com.example.silkwaytransit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.silkwaytransit.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
//    private List<LatLng> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String role = intent.getStringExtra("role");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder()
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        switch (role){
            case "Driver":
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.bottom_nav_driver);
                navGraph.setStartDestination(R.id.navigation_driver);
                break;
            case "Duty":
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.bottom_nav_duty);
                navGraph.setStartDestination(R.id.navigation_duty);
                break;
            case "Admin":
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.bottom_nav_admin);
                navGraph.setStartDestination(R.id.navigation_admin);
                break;
        }
        NavigationUI.setupWithNavController(navView, navController);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}

