package com.example.taipeizoo;

import android.os.Bundle;

import com.example.taipeizoo.Fragment.HomeFragment;
import com.example.taipeizoo.Util.UtilCommonStr;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.taipeizoo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilCommonStr.getInstance().mOutSideArea = getString(R.string.strOutDoorArea);
        UtilCommonStr.getInstance().mInSideArea = getString(R.string.strInDoorArea);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.mFragment, new HomeFragment(), "HomeFragment")
                .commit();
    }
}