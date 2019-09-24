package com.karimun.fordperformanceact;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.karimun.fordperformanceact.Fragments.EventCalendarFragment;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment2;
import com.karimun.fordperformanceact.Fragments.HomeFragment;
import com.karimun.fordperformanceact.Fragments.ManageMembersFragment;


public class MainActivity extends AppCompatActivity {

    public static TextView mainAppTitle;
    public static LinearLayout viewWrapper;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    boolean switchedToEventsCalendar2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        mainAppTitle = findViewById(R.id.main_app_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        Button btnSignout = navigationView.getHeaderView(0).findViewById(R.id.button_signout);
        viewWrapper = findViewById(R.id.view_wrapper);
        viewWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switchedToEventsCalendar2) {
                    FragmentTransaction fragmentTransactionCalendar2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionCalendar2.replace(R.id.fragment_container, new EventCalendarFragment2()).commit();
                    switchedToEventsCalendar2 = true;
                }
                else {
                    FragmentTransaction fragmentTransactionCalendarOriginal = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionCalendarOriginal.replace(R.id.fragment_container, new EventCalendarFragment()).commit();
                    switchedToEventsCalendar2 = false;
                }
            }
        });

        setSupportActionBar(toolbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();


        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_navigation, R.string.close_navigation);


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_hamburger);
        }

        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {
                    case R.id.nav_home:
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.fragment_container, new HomeFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Home");
                        viewWrapper.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.nav_calendar:
                        FragmentTransaction fragmentTransactionCalendar = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionCalendar.replace(R.id.fragment_container, new EventCalendarFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Events Calendar");
                        viewWrapper.setVisibility(View.VISIBLE);
                        break;

                    case R.id.nav_members_cars:
                        break;

                    case R.id.nav_membership:
                        break;

                    case R.id.nav_forums:
                        break;

                    case R.id.nav_gallery:
                        break;

                    case R.id.nav_sponsors:
                        break;

                    case R.id.nav_shop_merchandise:
                        break;

                    case R.id.nav_manage_members:
                        FragmentTransaction fragmentTransactionForManageMembers = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionForManageMembers.replace(R.id.fragment_container, new ManageMembersFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Manage Members");
                        viewWrapper.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.nav_admin_log:
                        break;
                }
                return true;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_social_media).getActionView().findViewById(R.id.nav_instagram)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "This will direct you to instagram", Toast.LENGTH_SHORT).show();
                    }
                });

        navigationView.getMenu().findItem(R.id.nav_social_media).getActionView().findViewById(R.id.nav_facebook)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "This will direct you to facebook", Toast.LENGTH_SHORT).show();
                    }
                });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {

            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}