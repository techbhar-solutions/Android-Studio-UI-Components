package com.example.retrofitexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this, SystemBarStyle.dark(Color.parseColor("#FF6D1F")),
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        );
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.content_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewswitcher);


        Toolbar  toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);


        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            if (position == 0) {
                tab.setText("Home");
            }
            else {
                tab.setText("Profile");
            }
        }).attach();

// 3. MENU CLICKS HANDLE KARNA
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Home Fragment load karo
            } else if (id == R.id.nav_profile) {
                // Profile Fragment load karo
            } else if (id == R.id.nav_logout) {
                // Logout logic
            }

            // Click hone ke baad drawer band kar do
            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        if (id == R.id.action_share) {
            Toast.makeText(this, "Action Share Is Clicked!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_language) {
            Toast.makeText(this, "Action Language Is Clicked", Toast.LENGTH_SHORT).show();
            // Yahan Logout ka logic likhenge
            return true;
        } else if (id == R.id.action_info) {
            Toast.makeText(this, "Action Info Is Clicked", Toast.LENGTH_SHORT).show();
            
        } else if (id == R.id.action_setting) {
            Toast.makeText(this, "Action Settings Is Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_rate) {
            Toast.makeText(this, "Action Rating Is Clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
