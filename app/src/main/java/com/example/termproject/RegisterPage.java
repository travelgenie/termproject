package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText RegEmail,RegPassword,RegSecurity;
    Button RegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        RegEmail=findViewById(R.id.regemail);
        RegPassword=findViewById(R.id.regpassword);
        RegSecurity=findViewById(R.id.regsecurity);
        RegButton=findViewById(R.id.createaccountnt);

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RegEmail.getText().toString().equals(""))
                {
                    RegEmail.setError("Enter Email");
                }
                else if(RegPassword.getText().toString().equals(""))
                {
                    RegPassword.setError("Enter Password");
                }
                else if(RegSecurity.getText().toString().equals(""))
                {
                    RegSecurity.setError("Enter Security");
                }
                else
                {
                    RegistrationUser UserDetails=new RegistrationUser(RegisterPage.this);
                    UserDetails.setReg_email(RegEmail.getText().toString());
                    UserDetails.setReg_pass(RegPassword.getText().toString());
                    UserDetails.setReg_security(RegSecurity.getText().toString());
                    Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }


}