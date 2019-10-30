package com.karimun.fordperformanceact;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.jgabrielfreitas.core.BlurImageView;
import com.karimun.fordperformanceact.Models.Member;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseUser currentMember;
    TextView usernameValue, firstNameValue, lastNameValue, spouseNameValue, addressValue, suburbValue, stateValue, postcodeValue, mobileValue, emailValue;

    BlurImageView backgroundProfileImage;
    CircleImageView profileImage;
    ImageView goToGallery;

    Uri selectedProfileImage;
    StorageReference storageReference;
    StorageTask storageTask;

    String finalUri;

    HashMap<String, Object> hashMap;

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        currentMember = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("uploaded_profile_pictures");

        Toolbar toolbar = findViewById(R.id.edit_profile_toolbar);
        usernameValue = findViewById(R.id.username_value);
        firstNameValue = findViewById(R.id.first_name_value);
        lastNameValue = findViewById(R.id.last_name_value);
        spouseNameValue = findViewById(R.id.spouse_name_value);
        addressValue = findViewById(R.id.address_value);
        suburbValue = findViewById(R.id.suburb_value);
        stateValue = findViewById(R.id.state_value);
        postcodeValue = findViewById(R.id.postcode_value);
        mobileValue = findViewById(R.id.mobile_value);
        emailValue = findViewById(R.id.email_value);

        profileImage = findViewById(R.id.profile_image);
        goToGallery = findViewById(R.id.go_to_gallery);

        TextView[] userDetails =
                {usernameValue, firstNameValue, lastNameValue, spouseNameValue, addressValue, suburbValue, stateValue, postcodeValue, mobileValue, emailValue};

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


        backgroundProfileImage = findViewById(R.id.background_profile_image);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(currentMember.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {
                    if (member.getImageUrl().equals("default")) {

                        backgroundProfileImage.setImageResource(R.drawable.image_default);
                        backgroundProfileImage.setBlur(5);
                        profileImage.setImageResource(R.drawable.image_default);

                    } else {

                        Glide.with(getApplicationContext()).load(member.getImageUrl()).into(profileImage);
                        Glide.with(getApplicationContext()).load(member.getImageUrl()).into(backgroundProfileImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        goToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        setUserDetails(userDetails);
    }

    private void setUserDetails(final TextView... textViews) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(currentMember.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Member member = dataSnapshot.getValue(Member.class);

                if (member != null) {
                    textViews[0].setText(member.getUsername());
                    textViews[1].setText(member.getFirstName());
                    textViews[2].setText(member.getSurname());
                    textViews[3].setText(member.getSpouseName());
                    textViews[4].setText(member.getAddress());
                    textViews[5].setText(member.getSuburb());
                    textViews[6].setText(member.getState());
                    textViews[7].setText(member.getPostcode());
                    textViews[8].setText(member.getMobilePhone());
                    textViews[9].setText(member.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PICTURE);
    }

    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadProfileImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.MyProgressDialog);
        progressDialog.setTitle("Uploading image..");
        progressDialog.show();

        if (selectedProfileImage != null) {
            final StorageReference fileReference = storageReference.child(currentMember.getUid() + System.currentTimeMillis() + "." + getExtension(selectedProfileImage));
            storageTask = fileReference.putFile(selectedProfileImage);
            storageTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        finalUri = downloadUri.toString();

                        hashMap = new HashMap<>();
                        hashMap.put("imageUrl", finalUri);

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Member").child(currentMember.getUid());
                        reference.updateChildren(hashMap);

                        Glide.with(EditProfileActivity.this).load(finalUri).into(profileImage);
                        Glide.with(EditProfileActivity.this).load(finalUri).into(backgroundProfileImage);
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(EditProfileActivity.this, "There was a problem while uploading picture.", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(EditProfileActivity.this, "No image selected.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedProfileImage = data.getData();

            if (storageTask != null && storageTask.isInProgress()) {
                Toast.makeText(EditProfileActivity.this, "Picture is being uploaded.", Toast.LENGTH_SHORT).show();
            } else {
                uploadProfileImage();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
