package com.example.yogeshramakrishnan_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MortgageResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgage_result_activity);

        String currentUserName = getIntent().getStringExtra("currentUserName");
        double borrowingAmount = getIntent().getDoubleExtra("borrowingAmount", 0);
        double depositAmount = getIntent().getDoubleExtra("depositAmount", 0);
        double mortgageDuration = getIntent().getDoubleExtra("mortgageDuration", 0);
        double monthlyPayment = getIntent().getDoubleExtra("monthlyPayment", 0);
        double totalAmount = getIntent().getDoubleExtra("totalAmount", 0);

        TextView currentUserNameTextView = findViewById(R.id.currentUserNameTextView);
        TextView borrowingAmountTextView = findViewById(R.id.borrowingAmountTextView);
        TextView depositAmountTextView = findViewById(R.id.depositAmountTextView);
        TextView mortgageDurationTextView = findViewById(R.id.mortgageDurationTextView);
        TextView monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        TextView totalAmountTextView = findViewById(R.id.totalAmountTextView);

        currentUserNameTextView.setText(String.format("Customer Name: %s", currentUserName));
        borrowingAmountTextView.setText(String.format("Borrowing Amount: £%.2f", borrowingAmount));
        depositAmountTextView.setText(String.format("Deposit Amount: £%.2f", depositAmount));
        mortgageDurationTextView.setText(String.format("Mortgage Duration: £%.2f", mortgageDuration));
        monthlyPaymentTextView.setText(String.format("Monthly Payment: £%.2f", monthlyPayment));
        totalAmountTextView.setText(String.format("Total Amount: £%.2f", totalAmount));
    }
}

