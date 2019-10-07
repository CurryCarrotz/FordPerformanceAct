package com.karimun.fordperformanceact;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

public class EditProfileActivity extends AppCompatActivity{

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    ImageButton btnProfilePic;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.edit_profile_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    finish();
                }
            });
        }
        imageToUpload = findViewById(R.id.profile_Pic);
        btnProfilePic = findViewById(R.id.button_ProfilePic);
    }
}
