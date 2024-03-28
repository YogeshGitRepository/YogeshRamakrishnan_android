package com.example.yogeshramakrishnan_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinancialDataReportActivity extends AppCompatActivity {
    private TextView takeHomeWageTextView;
    private TextView fixedOutgoingsTextView;
    private TextView currentRentOrMortgageTextView;
    private TextView leftoverWageTextView;
    private TextView borrowAmountTextView;
    private TextView depositAmountTextView;
    private Button btnbackHome;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_report_entry);

        // Initialize DBAdapter
        dbAdapter = new DBAdapter(this);

        // Assign views from XML layout
        takeHomeWageTextView = findViewById(R.id.takeHomeWageText);
        fixedOutgoingsTextView = findViewById(R.id.fixedOutgoingsText);
        currentRentOrMortgageTextView = findViewById(R.id.currentRentOrMortgageText);
        leftoverWageTextView = findViewById(R.id.leftoverWageText);
        borrowAmountTextView = findViewById(R.id.borrowAmountText);
        depositAmountTextView = findViewById(R.id.depositAmountText);
        btnbackHome = findViewById(R.id.backHome);

        // Retrieve user email from intent
        String userEmail = getIntent().getStringExtra("useremail");

        // Get current user's financial data if exists
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        boolean financialDataExists = dbAdapter.financialDataExists(currentUserId);

        if (financialDataExists) {
            FinancialDataMain financialData = dbAdapter.getFinancialData(currentUserId);
            if (financialData != null) {
                // Populate TextView fields with the retrieved data
                //String.format("Borrowing Amount: £%.2f K", financialData.getTakeHomeWage()));
                takeHomeWageTextView.setText(String.format("Average monthly take home wage: £%.2f", financialData.getTakeHomeWage()));
                fixedOutgoingsTextView.setText(String.format("Fixed monthly outgoings excluding rent or mortgage: £%.2f",financialData.getFixedOutgoings()));
                currentRentOrMortgageTextView.setText(String.format("Current monthly rental cost or mortgage: £%.2f",financialData.getCurrentRentOrMortgage()));
                leftoverWageTextView.setText(String.format("Wage is currently left at the end of a typical month: £%.2f",financialData.getLeftoverWage()));
                borrowAmountTextView.setText(String.format("Amount want to borrow: £%.2f",financialData.getDefaultBorrowAmount()));
                depositAmountTextView.setText(String.format("Size of deposit: £%.2f",financialData.getDefaultDepositAmount()));
            }
        }

        btnbackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomeActivity
                Intent intent = new Intent(FinancialDataReportActivity.this, HomeActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });
    }
}
