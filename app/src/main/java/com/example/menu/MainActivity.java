package com.example.menu;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    LinearLayout navDrawer;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navDrawer = findViewById(R.id.nav_drawer);

        findViewById(R.id.one_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new FirstFragment());
                drawerLayout.closeDrawers();
            }
        });

        findViewById(R.id.two_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SecondFragment());
                drawerLayout.closeDrawers();
            }
        });

        findViewById(R.id.three_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ThirdFragment());
                drawerLayout.closeDrawers();
            }
        });

        View navDrawer = findViewById(R.id.nav_drawer);
        ViewGroup.LayoutParams params = navDrawer.getLayoutParams();
        params.width = (int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.75);
        navDrawer.setLayoutParams(params);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setFragment(new FirstFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.first_item) {
                    setFragment(new FirstFragment());
                    return true;
                } else if (itemId == R.id.second_item) {
                    setFragment(new SecondFragment());
                    return true;
                } else if (itemId == R.id.third_item) {
                    setFragment(new ThirdFragment());
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        menu.add(0, R.id.action_dehaze, 0, "Меню")
                .setIcon(R.drawable.baseline_dehaze_24)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_dehaze) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Настройки выбраны", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment, null)
                .commit();
    }
}