package com.karimun.fordperformanceact;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment2;
import com.karimun.fordperformanceact.Fragments.EventPhotosFragment;
import com.karimun.fordperformanceact.Fragments.HomeFragment;
import com.karimun.fordperformanceact.Fragments.ManageMembersFragment;
import com.karimun.fordperformanceact.Fragments.MembershipFragment;
import com.karimun.fordperformanceact.Fragments.SponsorsFragment;
import com.karimun.fordperformanceact.Models.Member;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    public static AppBarLayout appBarLayout;
    public static TextView mainAppTitle;
    public static LinearLayout viewWrapper;

    public static DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static ActionBarDrawerToggle toggle;

    public static boolean switchedToEventsCalendar2 = false;

    FirebaseUser user;

    public static FrameLayout fragmentContainer;

    public static List<Fragment> openedNotInBackStackFragments;

    public static boolean isCurrentFragmentHome = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = findViewById(R.id.app_bar_layout);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        mainAppTitle = findViewById(R.id.main_app_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        final CircleImageView profileImage = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        final TextView username = navigationView.getHeaderView(0).findViewById(R.id.username);
        final TextView email = navigationView.getHeaderView(0).findViewById(R.id.email_address);
        LinearLayout editProfileWrapper = navigationView.getHeaderView(0).findViewById(R.id.edit_profile_wrapper);
        Button btnSignout = navigationView.getHeaderView(0).findViewById(R.id.button_signout);
        fragmentContainer = findViewById(R.id.fragment_container);
        viewWrapper = findViewById(R.id.view_wrapper);

        openedNotInBackStackFragments = new ArrayList<>();

        viewWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!switchedToEventsCalendar2) {
                    EventCalendarFragment2 eventCalendarFragment2 = new EventCalendarFragment2();
                    openedNotInBackStackFragments.add(eventCalendarFragment2);
                    changeFragment(eventCalendarFragment2);
                    switchedToEventsCalendar2 = true;
                } else {
                    EventCalendarFragment eventCalendarFragment = new EventCalendarFragment();
                    openedNotInBackStackFragments.add(eventCalendarFragment);
                    changeFragment(eventCalendarFragment);
                    switchedToEventsCalendar2 = false;
                }
            }
        });

        setSupportActionBar(toolbar);

        // Set default fragment
        changeFragment(new HomeFragment());


        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_navigation, R.string.close_navigation) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                hideSoftKeyboard(drawerView);
            }
        };


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

                    if (member.getImageUrl().equals("default")) {

                        profileImage.setImageResource(R.drawable.image_default);
                    } else {
                        Glide.with(getApplicationContext()).load(member.getImageUrl()).into(profileImage);
                    }

                    username.setText(member.getUsername());
                    email.setText(member.getEmail());

                    if (member.isAdmin()) {
                        navigationView.getMenu().getItem(8).setVisible(true);
                    } else {
                        navigationView.getMenu().getItem(8).setVisible(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // If edit profile option in the side navigation bar is clicked
        editProfileWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
            }
        });

        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();

                switch (itemId) {
                    case R.id.nav_home:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        viewWrapper.setVisibility(View.INVISIBLE);
                        changeFragmentBackToHome();
                        break;

                    case R.id.nav_calendar:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Events Calendar");
                        viewWrapper.setVisibility(View.VISIBLE);
                        switchedToEventsCalendar2 = false;

                        EventCalendarFragment eventCalendarFragment = new EventCalendarFragment();
                        openedNotInBackStackFragments.add(eventCalendarFragment);
                        changeFragment(eventCalendarFragment);
                        break;

                    case R.id.nav_members_cars:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Members Cars");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        EventPhotosFragment eventPhotosFragment2 = new EventPhotosFragment();
                        openedNotInBackStackFragments.add(eventPhotosFragment2);
                        changeFragment(eventPhotosFragment2);
                        break;

                    case R.id.nav_membership:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Membership");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        MembershipFragment membershipFragment = new MembershipFragment();
                        openedNotInBackStackFragments.add(membershipFragment);
                        changeFragment(membershipFragment);
                        break;

                    case R.id.nav_forums:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fordforums.com.au/forumdisplay.php?f=65"));
                        startActivity(intent);
                        break;

                    case R.id.nav_gallery:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Event Photos");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        EventPhotosFragment eventPhotosFragment = new EventPhotosFragment();
                        openedNotInBackStackFragments.add(eventPhotosFragment);
                        changeFragment(eventPhotosFragment);
                        break;

                    case R.id.nav_sponsors:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Club Sponsors");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        SponsorsFragment sponsorsFragment = new SponsorsFragment();
                        openedNotInBackStackFragments.add(sponsorsFragment);
                        changeFragment(sponsorsFragment);
                        break;

                    case R.id.nav_shop_merchandise:
                        break;

                    case R.id.nav_manage_members:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainAppTitle.setText("Manage Members");
                        viewWrapper.setVisibility(View.INVISIBLE);

                        ManageMembersFragment manageMembersFragment = new ManageMembersFragment();
                        openedNotInBackStackFragments.add(manageMembersFragment);
                        changeFragment(manageMembersFragment);
                        break;
                }
                return true;
            }
        });

        navigationView.getMenu().findItem(R.id.nav_social_media).getActionView().findViewById(R.id.nav_instagram)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fordperformanceclubofact/")));
                    }
                });

        navigationView.getMenu().findItem(R.id.nav_social_media).getActionView().findViewById(R.id.nav_facebook)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fordperformanceact/")));
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

            isCurrentFragmentHome = false;
        }
    }

    private void changeFragmentBackToHome() {
        if (getSupportFragmentManager() != null) {

            for (Fragment fragment : openedNotInBackStackFragments) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            getSupportFragmentManager().popBackStack();
            isCurrentFragmentHome = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            if (!isCurrentFragmentHome) {
                changeFragmentBackToHome();
            }
            else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
    }
}