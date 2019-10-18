package com.karimun.fordperformanceact.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.karimun.fordperformanceact.EditProfileActivity;
import com.karimun.fordperformanceact.LoginActivity;
import com.karimun.fordperformanceact.MainActivity;
import com.karimun.fordperformanceact.Models.Member;
import com.karimun.fordperformanceact.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    LinearLayout itemCalendar, itemMembersCars, itemMembership, itemForums, itemEventsPhotos, itemShopMerchandise, itemPayment, itemManageMembers, itemPostAnnouncement, itemAdminLog;
    ImageView itemInstagram, itemFacebook;
    CircleImageView profilePicture;
    TextView username, emailAddress;
    LinearLayout editProfile, resetPassword;
    Button btnSignout;

    FirebaseUser fUser;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fUser = FirebaseAuth.getInstance().getCurrentUser();

        MainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        MainActivity.toggle.setDrawerIndicatorEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        MainActivity.appBarLayout.setVisibility(View.GONE);

        // Assign values to each homepage menu item
        itemCalendar = view.findViewById(R.id.calendar_wrapper);
        itemMembersCars = view.findViewById(R.id.members_cars_wrapper);
        itemMembership = view.findViewById(R.id.membership_wrapper);
        itemForums = view.findViewById(R.id.forums_wrapper);
        itemEventsPhotos = view.findViewById(R.id.events_photos_wrapper);
        itemShopMerchandise = view.findViewById(R.id.shop_merchandise_wrapper);
        itemPayment = view.findViewById(R.id.payment_wrapper);
        itemManageMembers = view.findViewById(R.id.manage_members_wrapper);
        itemPostAnnouncement = view.findViewById(R.id.post_announcement_wrapper);
        itemAdminLog = view.findViewById(R.id.admin_log_wrapper);
        itemInstagram = view.findViewById(R.id.nav_instagram);
        itemFacebook = view.findViewById(R.id.nav_facebook);

        // Assign values to homepage header
        profilePicture = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.username);
        emailAddress = view.findViewById(R.id.email_address);
        btnSignout = view.findViewById(R.id.button_signout);

        // Assign values to edit profile and reset password variables
        editProfile = view.findViewById(R.id.edit_profile_wrapper);
        resetPassword = view.findViewById(R.id.reset_password_wrapper);

        // Set profile picture, username, and email address to the header of home page
        setUserDetails(profilePicture, username, emailAddress);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                if (getActivity() != null)
                    getActivity().finish();
            }
        });

        itemCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Events Calendar");
                MainActivity.viewWrapper.setVisibility(View.VISIBLE);
                MainActivity.switchedToEventsCalendar2 = false;
                MainActivity.isCurrentFragmentHome = false;
                changeFragment(new EventCalendarFragment());
            }
        });

        itemMembersCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Members Cars");
                //changeFragment(new MembersCarsFragment());
            }
        });

        itemMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Membership");
                //changeFragment(new MembershipFragment());
            }
        });

        itemForums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Forums");
                //changeFragment(new ForumsFragment());
            }
        });

        itemEventsPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Events Photos");
                //changeFragment(new EventsPhotosFragment());
            }
        });

        itemShopMerchandise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Shop Merchandise");
                //changeFragment(new ShopMerchandiseFragment());
            }
        });

        itemPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Payment");
                //changeFragment(new PaymentFragment());
            }
        });

        itemManageMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Manage Members");
                MainActivity.viewWrapper.setVisibility(View.INVISIBLE);
                MainActivity.isCurrentFragmentHome = false;
                changeFragment(new ManageMembersFragment());
            }
        });

        itemPostAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Post Announcement");
                //changeFragment(new PostAnnouncementFragment());
            }
        });

        itemAdminLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainAppTitle.setText("Admin Log");
                //changeFragment(new AdminLogFragment());
            }
        });

        itemInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/fordperformanceclubofact/")));
            }
        });

        itemFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fordperformanceact/")));
            }
        });
    }

    private void changeFragment(Fragment newFragment) {
        if (getFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container, newFragment).commit();
        }
    }

    private void setUserDetails(final CircleImageView circleImageView, final TextView... textViews) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(fUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {

                    if (member.getImageUrl().equals("default")) {
                        circleImageView.setImageResource(R.drawable.image_default);
                    } else {
                        if (getContext() != null)
                            Glide.with(getContext()).load(member.getImageUrl()).into(circleImageView);
                    }

                    textViews[0].setText(member.getUsername());
                    textViews[1].setText(member.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
