package com.karimun.fordperformanceact;

import android.app.AlertDialog;
import android.content.Intent;
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
