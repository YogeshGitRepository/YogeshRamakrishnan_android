package com.example.yogeshramakrishnan_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity  extends AppCompatActivity{
    private Button financialData, mortgageCalc,signOutButton;
    private DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        dbAdapter = new DBAdapter(this);

        financialData = findViewById(R.id.financialData);
        mortgageCalc = findViewById(R.id.mortgageCalc);
        signOutButton = findViewById(R.id.signOutButton);

        String userEmail = getIntent().getStringExtra("useremail");

        financialData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FinancialDataEntryActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });
        mortgageCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MortgageCalculatorActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
                 }
        });
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
             //   intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });
    }
}
