package com.karimun.fordperformanceact.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.karimun.fordperformanceact.MainActivity;
import com.karimun.fordperformanceact.R;

public class SponsorsFragment extends Fragment {

    LinearLayout sponJohn, sponKaleen, sponGenTech, sponCanberra, sponShannons, sponLeatherfx, sponAct, sponTintACar, sponCapitalSigns, sponAllType, sponReflection, sponAllStates;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sponsors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        MainActivity.toggle.setDrawerIndicatorEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
        MainActivity.appBarLayout.setVisibility(View.VISIBLE);

        sponJohn = view.findViewById(R.id.spon_johnmcgrathford);
        sponKaleen = view.findViewById(R.id.spon_kaleen);
        sponGenTech = view.findViewById(R.id.spon_gentech);
        sponCanberra = view.findViewById(R.id.spon_canberrabodyworks);
        sponShannons = view.findViewById(R.id.spon_shannons);
        sponLeatherfx = view.findViewById(R.id.spon_leatherfx);
        sponAct = view.findViewById(R.id.spon_actdiffdr);
        sponTintACar = view.findViewById(R.id.spon_tintacar);
        sponCapitalSigns = view.findViewById(R.id.spon_capitalsigns);
        sponAllType = view.findViewById(R.id.spon_alltypebatterysolutions);
        sponReflection = view.findViewById(R.id.spon_reflection);
        sponAllStates = view.findViewById(R.id.spon_allstatestowing);

        setOnClickListenerToLinearLayouts(sponJohn, sponKaleen, sponGenTech, sponCanberra, sponShannons, sponLeatherfx, sponAct, sponTintACar, sponCapitalSigns, sponAllType, sponReflection, sponAllStates);
    }

    private void setOnClickListenerToLinearLayouts(LinearLayout... linearLayouts) {

        linearLayouts[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://johnmcgrathford.com.au/")));
            }
        });

        linearLayouts[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://eastlakefc.com.au/index.php")));
            }
        });

        linearLayouts[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gentechperformance.com/")));
            }
        });

        linearLayouts[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.canberrabodyworks.com.au/")));
            }
        });

        linearLayouts[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.shannons.com.au/")));
            }
        });

        linearLayouts[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.leatherfx.com.au/")));
            }
        });

        linearLayouts[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/diffdoctor/")));
            }
        });

        linearLayouts[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.canberratintacar.com.au/")));
            }
        });

        linearLayouts[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://capitalsigns.com.au/")));
            }
        });

        linearLayouts[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.batteriescanberra.com.au/")));
            }
        });

        linearLayouts[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://reflectiondetailing.com.au/")));
            }
        });

        linearLayouts[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.allstatestowing.com.au/")));
            }
        });
    }
}
