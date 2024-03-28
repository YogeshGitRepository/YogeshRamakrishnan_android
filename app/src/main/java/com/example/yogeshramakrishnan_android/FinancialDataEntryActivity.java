package com.example.yogeshramakrishnan_android;

import android.content.Intent;
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
    private Button updateButton;
    private Button deleteButton;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_data_entry);

        dbAdapter = new DBAdapter(this);
        saveButton = findViewById(R.id.saveButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        takeHomeWageEditText = findViewById(R.id.takeHomeWageEditText);
        fixedOutgoingsEditText = findViewById(R.id.fixedOutgoingsEditText);
        currentRentOrMortgageEditText = findViewById(R.id.currentRentOrMortgageEditText);
        leftoverWageEditText = findViewById(R.id.leftoverWageEditText);
        borrowAmountEditText = findViewById(R.id.borrowAmountEditText);
        depositAmountEditText = findViewById(R.id.depositAmountEditText);

        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        boolean financialDataExists = dbAdapter.financialDataExists(currentUserId);

        // Show or hide update and delete buttons based on data existence
        if (financialDataExists) {
            FinancialDataMain financialData = dbAdapter.getFinancialData(currentUserId);

            // Populate EditText fields with the retrieved data
            takeHomeWageEditText.setText(String.valueOf((int)financialData.getTakeHomeWage()));
            fixedOutgoingsEditText.setText(String.valueOf((int)financialData.getFixedOutgoings()));
            currentRentOrMortgageEditText.setText(String.valueOf((int)financialData.getCurrentRentOrMortgage()));
            leftoverWageEditText.setText(String.valueOf((int)financialData.getLeftoverWage()));
            borrowAmountEditText.setText(String.valueOf((int)financialData.getDefaultBorrowAmount()));
            depositAmountEditText.setText(String.valueOf((int)financialData.getDefaultDepositAmount()));

            updateButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFinancialData();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFinancialData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFinancialData();
            }
        });
    }

    private void updateFinancialData() {
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        FinancialDataMain financialData = dbAdapter.getFinancialData(currentUserId);

        if (financialData != null) {
            double takeHomeWage = Double.parseDouble(takeHomeWageEditText.getText().toString());
            double fixedOutgoings = Double.parseDouble(fixedOutgoingsEditText.getText().toString());
            double currentRentOrMortgage = Double.parseDouble(currentRentOrMortgageEditText.getText().toString());
            double leftoverWage = Double.parseDouble(leftoverWageEditText.getText().toString());
            double borrowAmount = Double.parseDouble(borrowAmountEditText.getText().toString());
            double depositAmount = Double.parseDouble(depositAmountEditText.getText().toString());

            boolean updated = dbAdapter.updateFinancialData(currentUserId, takeHomeWage, fixedOutgoings, currentRentOrMortgage, leftoverWage, borrowAmount, depositAmount);

            if (updated) {
                Toast.makeText(this, "Financial data updated successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FinancialDataEntryActivity.this, FinancialDataReportActivity.class).putExtra("useremail", userEmail));
            } else {
                Toast.makeText(this, "Failed to update financial data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to retrieve financial data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteFinancialData() {
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);

        boolean deleted = dbAdapter.deleteFinancialData(currentUserId);

        if (deleted) {
            Toast.makeText(this, "Financial data deleted successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FinancialDataEntryActivity.this, HomeActivity.class).putExtra("useremail", userEmail));
        } else {
            Toast.makeText(this, "Failed to delete financial data", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFinancialData() {
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        double takeHomeWage = Double.parseDouble(takeHomeWageEditText.getText().toString());
        double fixedOutgoings = Double.parseDouble(fixedOutgoingsEditText.getText().toString());
        double currentRentOrMortgage = Double.parseDouble(currentRentOrMortgageEditText.getText().toString());
        double leftoverWage = Double.parseDouble(leftoverWageEditText.getText().toString());
        double borrowAmount = Double.parseDouble(borrowAmountEditText.getText().toString());
        double depositAmount = Double.parseDouble(depositAmountEditText.getText().toString());

        long newRowId = dbAdapter.insertFinancialData(currentUserId, takeHomeWage, fixedOutgoings, currentRentOrMortgage, leftoverWage, borrowAmount, depositAmount);

        if (newRowId != -1) {
            Toast.makeText(this, "Financial data saved successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FinancialDataEntryActivity.this, FinancialDataReportActivity.class).putExtra("useremail", userEmail));
        } else {
            Toast.makeText(this, "Failed to save financial data", Toast.LENGTH_SHORT).show();
        }
    }
}
