package com.karimun.fordperformanceact;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog popupWindow;

    FirebaseUser user;

    // If user is still logged in, then skip this activity to the main activity
    @Override
    protected void onStart() {
        super.onStart();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameLogin = findViewById(R.id.username_login);
        final EditText passwordLogin = findViewById(R.id.password_login);
        Button btnLogin = findViewById(R.id.button_signin);
        TextView forgotPassword = findViewById(R.id.go_to_forgot_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameLogin.getText().toString(), passwordLogin.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Snackbar.make(view, "Wrong email or password.", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPopupWindow();
            }
        });
    }

    private void startPopupWindow() {
        // set main linearlayout within the pop-up window
        RelativeLayout relativeLayout = new RelativeLayout(LoginActivity.this);


        // set layoutparams for email field
        RelativeLayout.LayoutParams paramsForEmailField =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForEmailField.leftMargin = 100;
        paramsForEmailField.rightMargin = 100;
        paramsForEmailField.height = 90;

        // declare email field and set attributes to it
        EditText emailField = new EditText(LoginActivity.this);
        emailField.setId(R.id.password_login);
        emailField.setSingleLine(true);
        emailField.setBackgroundResource(R.drawable.field_popup);
        emailField.setTextSize(16);
        emailField.setPadding(20, 0, 0, 0);
        emailField.setLayoutParams(paramsForEmailField);


        // set layoutparams for submit button
        RelativeLayout.LayoutParams paramsForButtonSubmit =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForButtonSubmit.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsForButtonSubmit.addRule(RelativeLayout.BELOW, R.id.password_login);
        paramsForButtonSubmit.topMargin = 40;
        paramsForButtonSubmit.bottomMargin = 40;


        // declare submit button and set attributes to it
        Button btnSubmit = new Button(LoginActivity.this);
        btnSubmit.setText("Submit");
        btnSubmit.setBackgroundResource(R.drawable.btn_popup_window);
        btnSubmit.setPadding(200, 0, 200, 0);
        btnSubmit.setLayoutParams(paramsForButtonSubmit);


        // apply the required objects to their parent, linearlayout
        relativeLayout.addView(emailField);
        relativeLayout.addView(btnSubmit);


        // create alert dialog(pop-up window)
        builder = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialog);
        builder.setTitle("Forgot Password")
        .setMessage("\nPlease enter the email registered with your account. You will receive an email shortly to reset your password.")
        .setView(relativeLayout);



        popupWindow = builder.create();

        if (popupWindow.getWindow() != null) {
            
            popupWindow.getWindow().setBackgroundDrawableResource(R.drawable.background_popup_window);
        }
        popupWindow.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
