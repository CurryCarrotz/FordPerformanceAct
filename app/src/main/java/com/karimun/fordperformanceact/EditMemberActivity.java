package com.karimun.fordperformanceact;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;



public class EditMemberActivity extends AppCompatActivity {

    String usernameValue, firstNameValue, surnameValue, emailValue, memberRoleValue, membershipExpiryValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        Bundle bundle = getIntent().getExtras();

        Toolbar toolbar = findViewById(R.id.edit_member_toolbar);
        EditText username = findViewById(R.id.field_username);
        EditText firstName = findViewById(R.id.field_first_name);
        EditText surname = findViewById(R.id.field_surname);
        EditText email = findViewById(R.id.field_email);
        EditText membershipRole = findViewById(R.id.field_membership_role);
        EditText membershipExpiry = findViewById(R.id.field_membership_expiry);
        ImageView editMembershipRole = findViewById(R.id.edit_membership_role);
        ImageView editMembershipExpiry = findViewById(R.id.edit_membership_expiry);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                }
            });
        }

        BlurImageView imageView = findViewById(R.id.background_profile_image);
        Bitmap bmBackgroundImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.image_default);

        if (bmBackgroundImage != null) {
            imageView.setImageBitmap(bmBackgroundImage);
            imageView.setBlur(5);
        } else {
            Toast.makeText(this, "bmBackgroundImage is null", Toast.LENGTH_LONG).show();
        }

        // Render fields unclickable and hide soft keyboard if soft keyboard somehow appears
        setAttrOnEditTexts(username, firstName, surname, email, membershipRole, membershipExpiry);

        // Set OnClick listener on member role and expiry
        editableMemberDetailsOnClick(EditMemberActivity.this, editMembershipRole, editMembershipExpiry);


        if (bundle != null) {
            usernameValue = bundle.getString("username");
            firstNameValue = bundle.getString("firstName");
            surnameValue = bundle.getString("surname");
            emailValue = bundle.getString("email");
            memberRoleValue = bundle.getString("memberRole");
            membershipExpiryValue = bundle.getString("membershipExpiry");

            // Set user details to each field
            setUserDetails(username, firstName, surname, email, membershipRole, membershipExpiry);

        }
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) EditMemberActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void setAttrOnEditTexts(EditText... editTexts) {

        for (EditText editText : editTexts) {
            editText.setInputType(InputType.TYPE_NULL);

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard(v);
                }
            });
        }
    }

    private void setUserDetails(final EditText... editTexts) {

        editTexts[0].setText(usernameValue);
        editTexts[1].setText(firstNameValue);
        editTexts[2].setText(surnameValue);
        editTexts[3].setText(emailValue);
        editTexts[4].setText(memberRoleValue);
        editTexts[5].setText(membershipExpiryValue);
    }

    private void editableMemberDetailsOnClick(final Activity activity, final ImageView... imageViews) {

        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder memberRoleDialogBuilder = new AlertDialog.Builder(activity);
                memberRoleDialogBuilder.setView(R.layout.window_edit_member_role);

                AlertDialog memberRoleDialog = memberRoleDialogBuilder.create();
                memberRoleDialog.show();

                Button confirmChanges = memberRoleDialog.findViewById(R.id.confirm_changes);
            }
        });

        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder memberExpiryDialogBuilder = new AlertDialog.Builder(activity);
                memberExpiryDialogBuilder.setView(R.layout.window_edit_member_expiry);

                AlertDialog memberExpiryDialog = memberExpiryDialogBuilder.create();
                memberExpiryDialog.show();

                Button confirmChanges = memberExpiryDialog.findViewById(R.id.confirm_changes);
            }
         });
    }
}
