package com.example.yogeshramakrishnan_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the main activity after the splash screen duration
        Intent mainIntent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(mainIntent);
        finish(); // Close the splash screen activity to prevent the user from going back to it

    }
}