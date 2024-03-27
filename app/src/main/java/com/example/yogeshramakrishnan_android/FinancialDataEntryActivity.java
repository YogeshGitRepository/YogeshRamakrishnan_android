package com.example.yogeshramakrishnan_android;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinancialDataEntryActivity extends AppCompatActivity {
    private EditText takeHomeWageEditText;
    private EditText fixedOutgoingsEditText;
    private EditText currentRentOrMortgageEditText;
    private EditText leftoverWageEditText;
    private EditText borrowAmountEditText;
    private EditText depositAmountEditText;
    private Button saveButton;

    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_data_entry);

        takeHomeWageEditText = findViewById(R.id.takeHomeWageEditText);
        fixedOutgoingsEditText = findViewById(R.id.fixedOutgoingsEditText);
        currentRentOrMortgageEditText = findViewById(R.id.currentRentOrMortgageEditText);
        leftoverWageEditText = findViewById(R.id.leftoverWageEditText);
        borrowAmountEditText = findViewById(R.id.borrowAmountEditText);
        depositAmountEditText = findViewById(R.id.depositAmountEditText);
        saveButton = findViewById(R.id.saveButton);

        dbAdapter = new DBAdapter(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFinancialData();
            }
        });
    }

    private void saveFinancialData() {
        double take_home_wage = Double.parseDouble(takeHomeWageEditText.getText().toString());
        double fixed_outgoings = Double.parseDouble(fixedOutgoingsEditText.getText().toString());
        double current_rent_or_mortgage = Double.parseDouble(currentRentOrMortgageEditText.getText().toString());
        double leftover_wage = Double.parseDouble(leftoverWageEditText.getText().toString());
        double default_borrow_amount = Double.parseDouble(borrowAmountEditText.getText().toString());
        double default_deposit_amount = Double.parseDouble(depositAmountEditText.getText().toString());
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        long newRowId = dbAdapter.insertFinancialData(currentUserId,take_home_wage,fixed_outgoings,current_rent_or_mortgage,leftover_wage,default_borrow_amount,default_deposit_amount);

        if (newRowId != -1) {
            Toast.makeText(this, "Financial data saved successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FinancialDataEntryActivity.this, FinancialDataReportActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to save financial data", Toast.LENGTH_SHORT).show();
        }
    }
}
