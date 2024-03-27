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

        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        boolean financialDataExists = dbAdapter.financialDataExists(currentUserId);

        // Show or hide update and delete buttons based on data existence
        if (financialDataExists) {
            FinancialDataMain financialData = dbAdapter.getFinancialData(currentUserId);

            // Populate EditText fields with the retrieved data
            takeHomeWageEditText.setText(String.valueOf(financialData.getTakeHomeWage()));
            fixedOutgoingsEditText.setText(String.valueOf(financialData.getFixedOutgoings()));
            currentRentOrMortgageEditText.setText(String.valueOf(financialData.getCurrentRentOrMortgage()));
            leftoverWageEditText.setText(String.valueOf(financialData.getLeftoverWage()));
            borrowAmountEditText.setText(String.valueOf(financialData.getDefaultBorrowAmount()));
            depositAmountEditText.setText(String.valueOf(financialData.getDefaultDepositAmount()));


            updateButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
        } else {
            takeHomeWageEditText = findViewById(R.id.takeHomeWageEditText);
            fixedOutgoingsEditText = findViewById(R.id.fixedOutgoingsEditText);
            currentRentOrMortgageEditText = findViewById(R.id.currentRentOrMortgageEditText);
            leftoverWageEditText = findViewById(R.id.leftoverWageEditText);
            borrowAmountEditText = findViewById(R.id.borrowAmountEditText);
            depositAmountEditText = findViewById(R.id.depositAmountEditText);


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
                updateButton.setText("Update Financial Data");
                updateFinancialData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButton.setText("Delete Financial Data");
                deleteFinancialData();
            }
        });
    }

    private void updateFinancialData() {
        double take_home_wage = Double.parseDouble(takeHomeWageEditText.getText().toString());
        double fixed_outgoings = Double.parseDouble(fixedOutgoingsEditText.getText().toString());
        double current_rent_or_mortgage = Double.parseDouble(currentRentOrMortgageEditText.getText().toString());
        double leftover_wage = Double.parseDouble(leftoverWageEditText.getText().toString());
        double default_borrow_amount = Double.parseDouble(borrowAmountEditText.getText().toString());
        double default_deposit_amount = Double.parseDouble(depositAmountEditText.getText().toString());
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);

        // Call updateFinancialData method from DBAdapter
        boolean updated = dbAdapter.updateFinancialData(currentUserId, take_home_wage, fixed_outgoings, current_rent_or_mortgage, leftover_wage, default_borrow_amount, default_deposit_amount);

        if (updated) {
            Toast.makeText(this, "Financial data updated successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FinancialDataEntryActivity.this, FinancialDataReportActivity.class);
            intent.putExtra("useremail", userEmail);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to update financial data", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteFinancialData() {
        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);

        // Call deleteFinancialData method from DBAdapter
        boolean deleted = dbAdapter.deleteFinancialData(currentUserId);

        if (deleted) {
            Toast.makeText(this, "Financial data deleted successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FinancialDataEntryActivity.this, HomeActivity.class);
            intent.putExtra("useremail", userEmail);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to delete financial data", Toast.LENGTH_SHORT).show();
        }
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
            intent.putExtra("useremail", userEmail);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to save financial data", Toast.LENGTH_SHORT).show();
        }
    }
}
