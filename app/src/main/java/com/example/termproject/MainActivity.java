package com.example.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button registerbutton, forgotbutton, login_button;
    RegistrationUser registrationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerbutton = (Button) findViewById(R.id.createaccountnt);
        forgotbutton = (Button) findViewById(R.id.forgotpwd);
        login_button = findViewById(R.id.loginbutton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        email.setText("bhvesh@bb1.com");
        password.setText("123456");

        login_button.setOnClickListener(view -> {
            final String getEnteredEmail = email.getText().toString();
            final String enteredPassword = password.getText().toString();
            if (validateFields(getEnteredEmail, enteredPassword)) {
                registrationUser = new RegistrationUser(getApplicationContext());
                String Email = registrationUser.getReg_email();
                String Password = registrationUser.getReg_pass();
                // Validation
                if (!Email.equalsIgnoreCase(getEnteredEmail)) {
                    email.setError("No Email Found in records");
                    showMessage("No Email Found in records");
                } else if (!Password.equals(enteredPassword)) {
                    password.setError("Password do not match");
                } else {
                    Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                    intent.putExtra("User", getEnteredEmail);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerbutton.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), RegisterPage.class);
            startActivity(intent1);

        });
        forgotbutton.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(intent12);

        });
    }

    private Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private boolean validatePassword(String password) {
        return !password.isEmpty() && password.length() >= 5;
    }

    private boolean validateSecurity(String security) {
        return !security.isEmpty() && security.length() >= 5;
    }

    private boolean validateFields(String emailStr, String passwordStr) {
        if (!validateEmail(emailStr)) {
            email.setError("Incorrect Email");
            showMessage("Wrong Email");
            return false;
        } else if (!validatePassword(passwordStr)) {
            password.setError("Password short or empty");
            showMessage("Password short or empty");
            return false;
        }
        return true;
    }

    private boolean validateFields(String emailStr, String password, String security) {
        if (!validateEmail(emailStr)) {
            showMessage("Wrong Email");
            return false;
        } else if (!validatePassword(password)) {
            showMessage("Password short or empty");
            return false;
        } else if (!validateSecurity(security)) {
            showMessage("Security answer short or empty");
            return false;
        }
        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}