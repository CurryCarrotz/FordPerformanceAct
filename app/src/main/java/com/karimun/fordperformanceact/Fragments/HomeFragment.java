package com.karimun.fordperformanceact.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karimun.fordperformanceact.MainActivity;
import com.karimun.fordperformanceact.R;

public class HomeFragment extends Fragment {

    CardView itemEventsCalendar, itemCommitee, itemMembership, itemMerchandise, itemMembersCars, itemEventsGallery;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        MainActivity.toggle.setDrawerIndicatorEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        itemEventsCalendar = view.findViewById(R.id.item_events_calendar);
        itemCommitee = view.findViewById(R.id.item_commitee);
        itemMembership = view.findViewById(R.id.item_membership);
        itemMerchandise = view.findViewById(R.id.item_merchandise);
        itemMembersCars = view.findViewById(R.id.item_members_cars);
        itemEventsGallery = view.findViewById(R.id.item_events_gallery);

        itemEventsCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getFragmentManager() != null) {

                    changeFragment(new EventCalendarFragment());

                    MainActivity.mainAppTitle.setText("Events Calendar");
                    MainActivity.viewWrapper.setVisibility(View.VISIBLE);

                    MainActivity.switchedToEventsCalendar2 = false;
                }
            }
        });

    }

    private void changeFragment(Fragment newFragment) {
        if (getFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, newFragment).commit();
        }
    }
}
