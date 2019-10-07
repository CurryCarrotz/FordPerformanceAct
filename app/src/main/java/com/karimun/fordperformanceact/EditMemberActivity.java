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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jgabrielfreitas.core.BlurImageView;
import com.karimun.fordperformanceact.Models.Member;

import java.util.HashMap;


public class EditMemberActivity extends AppCompatActivity {

    String memberId, usernameValue, firstNameValue, surnameValue, emailValue, memberRoleValue, membershipExpiryValue;

    EditText membershipRole;
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
        membershipRole = findViewById(R.id.field_membership_role);
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
            Toast.makeText(this, "bmBackgroundImage is null.", Toast.LENGTH_LONG).show();
        }

        // Render fields unclickable and hide soft keyboard if soft keyboard somehow appears
        setAttrOnEditTexts(username, firstName, surname, email, membershipRole, membershipExpiry);

        // Set OnClick listener for member role and expiry
        editableMemberDetailsOnClick(EditMemberActivity.this, editMembershipRole, editMembershipExpiry);


        if (bundle != null) {
            memberId = bundle.getString("memberId");
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

                final AlertDialog memberRoleDialog = memberRoleDialogBuilder.create();
                memberRoleDialog.show();

                final EditText fieldOldMembershipRole = memberRoleDialog.findViewById(R.id.field_old_membership_role);
                final EditText fieldNewMembershipRole = memberRoleDialog.findViewById(R.id.field_new_membership_role);
                Button confirmChanges = memberRoleDialog.findViewById(R.id.confirm_changes);

                if (fieldOldMembershipRole != null) {
                    fieldOldMembershipRole.setInputType(InputType.TYPE_NULL);
                    fieldOldMembershipRole.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            hideSoftKeyboard(v);
                        }
                    });
                }

                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(memberId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Member member = dataSnapshot.getValue(Member.class);

                        if (member != null && fieldOldMembershipRole != null) {

                            fieldOldMembershipRole.setText(member.getMemberRole());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if (confirmChanges != null)
                    confirmChanges.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (fieldNewMembershipRole != null) {

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("memberRole", fieldNewMembershipRole.getText().toString());

                                if (!fieldNewMembershipRole.getText().toString().equalsIgnoreCase("Admin".trim())) {
                                    hashMap.put("isAdmin", false);
                                } else {
                                    hashMap.put("isAdmin", true);
                                }

                                reference.updateChildren(hashMap);

                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(memberId);
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Member member = dataSnapshot.getValue(Member.class);

                                        if (member != null) {

                                            membershipRole.setText(member.getMemberRole());
                                            memberRoleDialog.dismiss();
                                            Toast.makeText(EditMemberActivity.this,
                                                    "Member role updated!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                        }
                    });
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
