package com.example.letstalk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.letstalk.databinding.ActivityMainBinding;
import com.example.letstalk.ui.Constants;
import com.example.letstalk.utils.DBUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        if (DBUtils.getIsFirstTime()) {
            Log.d(Constants.TAG, "onCreate: getIsFirstTime true");
            DBUtils.saveIsFirstTime(false);
            for (int i = 0; i < 7; i++) {
                Log.d(Constants.TAG, "onCreate: item " + Constants.DATA.get(i));
                DBUtils.saveItem(Constants.DATA.get(i));
            }
        }else{
            Log.d(Constants.TAG, "onCreate: getIsFirstTime false");
        }

        setContentView(binding.getRoot());




        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}