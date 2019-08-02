package com.example.jointventureapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jointventureapp.R;

public class Trial extends AppCompatActivity {

    BottomNavigationView botNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trial);

        botNavView = findViewById(R.id.bottomNavTrial);
        Menu menu = botNavView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.addpg:
                        Intent i = new Intent(Trial.this, AddActivity.class);
                        startActivity(i);
                    case R.id.calpg:
                        Intent ii = new Intent(Trial.this, CalendarActivity.class);
                        startActivity(ii);
                        break;


                    case R.id.graphpg:
                        Intent iii = new Intent(Trial.this, GraphsActivity.class);
                        startActivity(iii);
                        break;
                }
                return false;
            }
        });
    }
}
