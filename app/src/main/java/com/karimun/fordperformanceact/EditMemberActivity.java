package com.karimun.fordperformanceact;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jgabrielfreitas.core.BlurImageView;
import com.karimun.fordperformanceact.Models.Member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditMemberActivity extends AppCompatActivity {

    ArrayList<String> memberRoles;

    CircleImageView profileImage;
    BlurImageView backgroundProfileImage;

    String memberId;

    EditText membershipRole, membershipExpiry;

    Button btnEditMemberDetails;

    String selectedMemberRole;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        Bundle bundle = getIntent().getExtras();

        memberRoles = new ArrayList<>();
        memberRoles.add("President");
        memberRoles.add("Vice President");
        memberRoles.add("Secretary");
        memberRoles.add("Treasurer");
        memberRoles.add("Developer");

        Toolbar toolbar = findViewById(R.id.edit_member_toolbar);
        profileImage = findViewById(R.id.profile_image);
        backgroundProfileImage = findViewById(R.id.background_profile_image);
        EditText username = findViewById(R.id.field_username);
        EditText firstName = findViewById(R.id.field_first_name);
        EditText surname = findViewById(R.id.field_surname);
        EditText email = findViewById(R.id.field_email);
        membershipRole = findViewById(R.id.field_membership_role);
        membershipExpiry = findViewById(R.id.field_membership_expiry);
        EditText memberSince = findViewById(R.id.field_member_since);
        EditText membershipRenewalPaid = findViewById(R.id.field_membership_renewal_paid);
        EditText admin = findViewById(R.id.field_admin);
        EditText activeMember = findViewById(R.id.field_active_member);
        btnEditMemberDetails = findViewById(R.id.btn_edit_member_details);


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

        // Render fields unclickable and hide soft keyboard if soft keyboard somehow appears
        setAttrOnEditTexts(username, firstName, surname, email, membershipRole, membershipExpiry, memberSince, membershipRenewalPaid, admin, activeMember);


        if (bundle != null) {
            memberId = bundle.getString("memberId");
            /*usernameValue = bundle.getString("username");
            firstNameValue = bundle.getString("firstName");
            surnameValue = bundle.getString("surname");
            emailValue = bundle.getString("email");
            memberRoleValue = bundle.getString("memberRole");
            membershipExpiryValue = bundle.getString("membershipExpiry");
            memberSinceValue = bundle.getString("memberSince");
            membershipRenewalPaidValue = bundle.getString("membershipRenewalPaid");
            isAdminValue = bundle.getBoolean("isAdmin");
            isActiveMemberValue = bundle.getBoolean("isActiveMember");*/


            // Set user details to each field
            setUserDetails(profileImage, backgroundProfileImage, username, firstName, surname, email, membershipRole, membershipExpiry, memberSince, membershipRenewalPaid, admin, activeMember);
        }

        btnEditMemberDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditMemberDetailsDialog(memberRoles);
            }
        });
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

    private void setUserDetails(final CircleImageView circleImageView, final BlurImageView blurImageView, final EditText... editTexts) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(memberId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {
                    if (member.getImageUrl().equals("default")) {

                        circleImageView.setImageResource(R.drawable.image_default);
                        blurImageView.setImageResource(R.drawable.image_default);
                        blurImageView.setBlur(5);
                    } else {
                        Glide.with(getApplicationContext()).load(member.getImageUrl()).into(circleImageView);
                        Glide.with(getApplicationContext()).load(member.getImageUrl()).into(blurImageView);
                    }

                    editTexts[0].setText(member.getUsername());
                    editTexts[1].setText(member.getFirstName());
                    editTexts[2].setText(member.getSurname());
                    editTexts[3].setText(member.getEmail());
                    editTexts[4].setText(member.getMemberRole());
                    editTexts[5].setText(member.getMembershipExpiry());
                    editTexts[6].setText(member.getMemberSince());
                    editTexts[7].setText(member.getMembershipRenewalPaid());
                    editTexts[8].setText(String.valueOf(member.isAdmin()));
                    editTexts[9].setText(String.valueOf(member.isActiveMember()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void selectNewExpiryDate(final EditText editText) {

        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                editText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditMemberActivity.this, R.style.MyDatePickerDialog, dateSetListener, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void selectRenewalPaidDate(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                editText.setText(dateFormat.format(calendar.getTime()));
            }
        };

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(EditMemberActivity.this, R.style.MyDatePickerDialog, dateSetListener, year, month, day);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void showEditMemberDetailsDialog(ArrayList<String> roles) {

        AlertDialog.Builder builder = new AlertDialog.Builder(EditMemberActivity.this);
        builder.setView(R.layout.window_edit_member_details);

        final AlertDialog editMemberDetailsDialog = builder.create();
        editMemberDetailsDialog.show();


        final EditText membershipExpiryDate = editMemberDetailsDialog.findViewById(R.id.field_membership_expiry_date);
        final Spinner membershipRole = editMemberDetailsDialog.findViewById(R.id.field_new_membership_role);
        final EditText renewalPaidDate = editMemberDetailsDialog.findViewById(R.id.field_renewal_paid_date);
        final CheckBox activeMemberCheckBox = editMemberDetailsDialog.findViewById(R.id.checkbox_active_member);
        final CheckBox adminCheckBox = editMemberDetailsDialog.findViewById(R.id.checkbox_admin);
        Button confirmChanges = editMemberDetailsDialog.findViewById(R.id.confirm_changes);

        setExistingValuesToPopupFields(membershipRole, membershipExpiryDate, renewalPaidDate, activeMemberCheckBox, adminCheckBox);

        if (membershipExpiryDate != null) {
            membershipExpiryDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard(v);
                    selectNewExpiryDate(membershipExpiryDate);
                }
            });
        }

        arrayAdapter = new ArrayAdapter<>(EditMemberActivity.this, android.R.layout.simple_spinner_dropdown_item, roles);

        if (membershipRole != null) {
            membershipRole.setAdapter(arrayAdapter);
            membershipRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedMemberRole = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        if (renewalPaidDate != null) {
            renewalPaidDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSoftKeyboard(v);
                    selectRenewalPaidDate(renewalPaidDate);
                }
            });
        }



        if (confirmChanges != null && membershipExpiryDate != null && membershipRole != null && renewalPaidDate != null) {
            confirmChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("membershipExpiry", membershipExpiryDate.getText().toString());
                    hashMap.put("memberRole", selectedMemberRole);
                    hashMap.put("membershipRenewalPaid", renewalPaidDate.getText().toString());

                    if (activeMemberCheckBox != null) {
                        if (activeMemberCheckBox.isChecked()) {
                            hashMap.put("isActiveMember", true);
                        } else {
                            hashMap.put("isActiveMember", false);
                        }
                    }

                    if (adminCheckBox != null) {
                        if (adminCheckBox.isChecked()) {
                            hashMap.put("isAdmin", true);
                        } else {
                            hashMap.put("isAdmin", false);
                        }
                    }

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(memberId);
                    reference.updateChildren(hashMap);

                    editMemberDetailsDialog.dismiss();
                    Toast.makeText(EditMemberActivity.this, "Details updated!", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void setExistingValuesToPopupFields(final Spinner role, final EditText expiry, final EditText renewal, final CheckBox active, final CheckBox admin) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(memberId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {
                    expiry.setText(member.getMembershipExpiry());
                    role.setSelection(arrayAdapter.getPosition(member.getMemberRole()));
                    renewal.setText(member.getMembershipRenewalPaid());

                    if (member.isActiveMember()) {
                        active.setChecked(true);
                    }
                    else {
                        active.setChecked(false);
                    }

                    if (member.isAdmin()) {
                        admin.setChecked(true);
                    }
                    else {
                        admin.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
