package com.example.yogeshramakrishnan_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerRedirectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerRedirectButton = findViewById(R.id.registerRedirectButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if email and password are not empty
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Here you can perform your login authentication
                    // For demonstration purposes, let's assume a hardcoded email and password
                    String validEmail = email;
                    String validPassword = password;

                    // Check if the entered credentials match the valid ones
                    if (email.equals(validEmail) && password.equals(validPassword)) {
                        // If credentials are valid, you can proceed with login
                        // For example, you can start the main activity
                        startActivity(new Intent(LoginActivity.this, MortgageCalculatorActivity.class));
                        finish(); // Close the login activity
                    } else {
                        // If credentials are invalid, show an error message
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If email or password is empty, show an error message
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        registerRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}

