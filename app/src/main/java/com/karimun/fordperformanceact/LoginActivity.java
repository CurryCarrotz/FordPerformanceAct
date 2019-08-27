package com.karimun.fordperformanceact;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.button_signin);
        TextView forgotPassword = findViewById(R.id.go_to_forgot_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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

        // set layoutparams for email label
        RelativeLayout.LayoutParams paramsForEmailLabel =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForEmailLabel.leftMargin = 100;
        paramsForEmailLabel.bottomMargin = 20;

        // declare email label and set attritubes to it
        TextView emailLabel = new TextView(LoginActivity.this);
        emailLabel.setId(R.id.username_login);
        emailLabel.setText("Email:");
        emailLabel.setTextSize(20);
        emailLabel.setTextColor(Color.BLACK);
        emailLabel.setTypeface(Typeface.DEFAULT_BOLD);
        emailLabel.setLayoutParams(paramsForEmailLabel);


        // set layoutparams for email field
        RelativeLayout.LayoutParams paramsForEmailField =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForEmailField.leftMargin = 100;
        paramsForEmailField.rightMargin = 100;
        paramsForEmailField.height = 90;
        paramsForEmailField.addRule(RelativeLayout.BELOW, R.id.username_login);

        // declare email field and set attributes to it
        EditText emailField = new EditText(LoginActivity.this);
        emailField.setId(R.id.password_login);
        emailField.setSingleLine(true);
        emailField.setBackgroundResource(R.drawable.field_popup_email);
        emailField.setTextSize(16);
        emailField.setPadding(20, 0, 0, 0);
        emailField.setLayoutParams(paramsForEmailField);


        // set layoutparams for submit button
        RelativeLayout.LayoutParams paramsForButtonSubmit =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForButtonSubmit.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsForButtonSubmit.addRule(RelativeLayout.BELOW, R.id.password_login);
        paramsForButtonSubmit.rightMargin = 100;
        paramsForButtonSubmit.topMargin = 40;
        paramsForButtonSubmit.bottomMargin = 40;


        // declare submit button and set attributes to it
        Button btnSubmit = new Button(LoginActivity.this);
        btnSubmit.setText("Submit");
        btnSubmit.setBackgroundResource(R.drawable.btn_popup_window);
        btnSubmit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_submit, 0);
        btnSubmit.setLayoutParams(paramsForButtonSubmit);

        // set layoutparams for cancel button
        RelativeLayout.LayoutParams paramsForButtonCancel =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        paramsForButtonCancel.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramsForButtonCancel.addRule(RelativeLayout.BELOW, R.id.password_login);
        paramsForButtonCancel.leftMargin = 100;
        paramsForButtonCancel.topMargin = 40;
        paramsForButtonCancel.bottomMargin = 40;

        // declare cancel button and set attributes to it
        Button btnCancel = new Button(LoginActivity.this);
        btnCancel.setText("Cancel");
        btnCancel.setBackgroundResource(R.drawable.btn_popup_window);
        btnCancel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_cancel, 0, 0, 0);
        btnCancel.setLayoutParams(paramsForButtonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // apply the required objects to their parent, linearlayout
        relativeLayout.addView(emailLabel);
        relativeLayout.addView(emailField);
        relativeLayout.addView(btnSubmit);
        relativeLayout.addView(btnCancel);


        // create alert dialog(pop-up window)
        builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Forgot Password?");
        builder.setMessage("\nTo request a password reset, please enter your registered email address.");
        builder.setView(relativeLayout);



        popupWindow = builder.create();

        if (popupWindow.getWindow() != null) {
            popupWindow.getWindow().setBackgroundDrawableResource(R.drawable.popup_window_background);
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
