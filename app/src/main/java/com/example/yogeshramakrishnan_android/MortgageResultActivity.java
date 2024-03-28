package com.example.yogeshramakrishnan_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MortgageResultActivity extends AppCompatActivity {
    private Button btnbackHome;
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
        double interestRate = getIntent().getDoubleExtra("InterestRate", 0);
        TextView currentUserNameTextView = findViewById(R.id.currentUserNameTextView);
        TextView borrowingAmountTextView = findViewById(R.id.borrowingAmountTextView);
        TextView depositAmountTextView = findViewById(R.id.depositAmountTextView);
        TextView mortgageDurationTextView = findViewById(R.id.mortgageDurationTextView);
        TextView monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        TextView totalAmountTextView = findViewById(R.id.totalAmountTextView);
        TextView InterestRateTextView= findViewById(R.id.InterestRateTextView);

        currentUserNameTextView.setText(String.format("Customer Name: %s", currentUserName));
        borrowingAmountTextView.setText(String.format("Borrowing Amount: £%.2f K", borrowingAmount));
        depositAmountTextView.setText(String.format("Deposit Amount: £%.2f K", depositAmount));
        mortgageDurationTextView.setText(String.format("Mortgage Duration: %s Years", (int)mortgageDuration));
        monthlyPaymentTextView.setText(String.format("Monthly Payment: £%.2f K", monthlyPayment));
        totalAmountTextView.setText(String.format("Total Amount: £%.2f K", totalAmount));
        InterestRateTextView.setText(String.format("Interest Rate : %s", interestRate *(100) + " %"));
        btnbackHome = findViewById(R.id.backHome);
        btnbackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MortgageResultActivity.this, HomeActivity.class);
                String userEmail = getIntent().getStringExtra("useremail");
                intent.putExtra("useremail", userEmail);
                startActivity(intent);

            }
        });
    }
}

