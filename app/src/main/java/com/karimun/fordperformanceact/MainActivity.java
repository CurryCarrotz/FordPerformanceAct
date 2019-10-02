package com.karimun.fordperformanceact;

import android.support.v4.app.Fragment;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment2;
import com.karimun.fordperformanceact.Fragments.HomeFragment;
import com.karimun.fordperformanceact.Fragments.ManageMembersFragment;
import com.karimun.fordperformanceact.Models.Member;


public class MainActivity extends AppCompatActivity {

    public static TextView mainAppTitle;
    public static LinearLayout viewWrapper;

    public static DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static ActionBarDrawerToggle toggle;

    public static boolean switchedToEventsCalendar2 = false;

    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        mainAppTitle = findViewById(R.id.main_app_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        final TextView username = navigationView.getHeaderView(0).findViewById(R.id.username);
        final TextView email = navigationView.getHeaderView(0).findViewById(R.id.email_address);
        Button btnSignout = navigationView.getHeaderView(0).findViewById(R.id.button_signout);
        viewWrapper = findViewById(R.id.view_wrapper);

        viewWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switchedToEventsCalendar2) {
                    changeFragment(new EventCalendarFragment2());
                    switchedToEventsCalendar2 = true;
                }
                else {
                    changeFragment(new EventCalendarFragment());
                    switchedToEventsCalendar2 = false;
                }
            }
        });

        setSupportActionBar(toolbar);

        // Set default fragment
        changeFragment(new HomeFragment());


        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_navigation, R.string.close_navigation);


        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        // Set user's username and email within side navigation bar
        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {
                    username.setText(member.getUsername());
                    email.setText(member.getEmail());

                    if (member.isAdmin()) {
                        navigationView.getMenu().getItem(8).setVisible(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {
                    case R.id.nav_home:
                        changeFragment(new HomeFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("      Home");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        break;

                    case R.id.nav_calendar:
                        changeFragment(new EventCalendarFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Events Calendar");
                        viewWrapper.setVisibility(View.VISIBLE);

                        switchedToEventsCalendar2 = false;

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
                        changeFragment(new ManageMembersFragment());
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

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void changeFragment(Fragment newFragment) {
        if (getSupportFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, newFragment)
                    .commit();
        }
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