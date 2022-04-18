package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText email_detail, security_detail, newpass_detail;
    Button reset_password;
    RegistrationUser registrationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email_detail = findViewById(R.id.forgotemail);
        security_detail = findViewById(R.id.forgotsecurity);
        newpass_detail = findViewById(R.id.forgotpassword);
        reset_password = findViewById(R.id.resetpwdbutton);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email_detail.getText().toString().equals("")) {
                    email_detail.setError("Enter Email");
                } else if (security_detail.getText().toString().equals("")) {
                    security_detail.setError("Enter Security");
                } else if (newpass_detail.getText().toString().equals("")) {
                    newpass_detail.setError("Enter Password");
                } else {
                    registrationUser = new RegistrationUser(getApplicationContext());
                    String Email = registrationUser.getReg_email();
                    String Security = registrationUser.getReg_security();

                    // Validation
                    if (!Email.equalsIgnoreCase(email_detail.getText().toString())) {
                        email_detail.setError("Incorrect Email");
                    } else if (!Security.equalsIgnoreCase(security_detail.getText().toString())) {
                        security_detail.setError("Incorrect Security");
                    } else {
                        registrationUser.setReg_pass(newpass_detail.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Password Change Successful", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
