package com.tablayout.basic;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tablayout.async.AsyncClientInterface;

import java.util.ArrayList;

/**
 *
 */

public class BasicActivity extends AppCompatActivity implements AsyncClientInterface,NavigationView.OnNavigationItemSelectedListener{
    @Override
    public void onSuccess(int statusCode,ArrayList arrayList) {

    }

    @Override
    public void onSuccess(ArrayList arrayList) {

    }

    public void onSuccess(String string) {

    }

    @Override
    public void onFailure(int statusCode) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
