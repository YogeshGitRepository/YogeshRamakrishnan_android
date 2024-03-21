package com.example.yogeshramakrishnan_android;

import static java.lang.String.*;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MortgageCalculatorActivity extends AppCompatActivity {

    private SeekBar borrowSeekBar;
    private SeekBar depositSeekBar;
    private SeekBar durationSeekBar;
    private TextView monthlyPaymentTextView;
    private TextView totalAmountTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgagecalculator_activity);

        borrowSeekBar = findViewById(R.id.borrowingAmountSeekBar);
        depositSeekBar = findViewById(R.id.depositSeekBar);
        durationSeekBar = findViewById(R.id.mortgageDurationSeekBar);
        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);


        borrowSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {calculateMortgage();}
        });
        depositSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {calculateMortgage();}
        });
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {calculateMortgage();}
        });

    }
    // Handle mortgage calculation logic and UI updates
    @SuppressLint("DefaultLocale")
    public void calculateMortgage() {
        // Get SeekBar values
        double borrowingAmount = borrowSeekBar.getProgress();
        double depositAmount = depositSeekBar.getProgress();
        int mortgageDuration = durationSeekBar.getProgress();

        // Calculate monthly payment and total amount
        // Implement your calculation logic here


        double annualInterestRate = 0.03; // 3%
        double monthlyInterestRate = annualInterestRate / 12;
        int numberOfMonths = mortgageDuration * 12;

        double principal = borrowingAmount - depositAmount;
        double power = Math.pow(1 + monthlyInterestRate, numberOfMonths);
        double divisor = power - 1;
        double monthlyPayment = principal * monthlyInterestRate * power / divisor;
        double totalAmount = monthlyPayment * numberOfMonths;

        monthlyPaymentTextView.setText(format("Monthly Payment: £%.2f", monthlyPayment));
        totalAmountTextView.setText(format("Total Amount: £%.2f", totalAmount));
        // Update TextViews with calculated values

    }
/*//    public void addOnclick (View view){
//        connect = new DBAdapter(this);
//    }*/
}