package com.example.yogeshramakrishnan_android;

import static java.lang.String.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


public class MortgageCalculatorActivity extends AppCompatActivity {

    private SeekBar borrowSeekBar;
    private SeekBar depositSeekBar;
    private SeekBar durationSeekBar;
    private TextView monthlyPaymentTextView;
    private TextView totalAmountTextView;
    private TextView  InterestRateTextView;
    private Button addButton;
    private DBAdapter dbAdapter;
    private TextView tooltipTextView,tooltipTextView1,tooltipTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgagecalculator_activity);

        borrowSeekBar = findViewById(R.id.borrowingAmountSeekBar);
        depositSeekBar = findViewById(R.id.depositSeekBar);
        durationSeekBar = findViewById(R.id.mortgageDurationSeekBar);
        monthlyPaymentTextView = findViewById(R.id.monthlyPaymentTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        addButton = findViewById(R.id.addButton);
        tooltipTextView = findViewById(R.id.tooltipTextView);
        tooltipTextView1 = findViewById(R.id.tooltipTextView1);
        tooltipTextView2 = findViewById(R.id.tooltipTextView2);
        // Initialize database adapter
        dbAdapter = new DBAdapter(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMortgage(); // Call method to save mortgage calculation
            }
        });

        calculateMortgage();
        borrowSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                // Display the tooltip TextView with updated text

                tooltipTextView.setText("£" + (progress)+ "K");
                tooltipTextView.setVisibility(View.VISIBLE);
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Hide the tooltip when tracking touch stops
                tooltipTextView.setVisibility(View.VISIBLE);
                calculateMortgage();
            }
        });
        depositSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                // Display the tooltip TextView with updated text

                tooltipTextView1.setText("£" + (progress) + "K");
                tooltipTextView1.setVisibility(View.VISIBLE);
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {
                tooltipTextView1.setVisibility(View.VISIBLE);
                calculateMortgage();}
        });
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                // Display the tooltip TextView with updated text
                tooltipTextView2.setText((progress) + "Years");
                tooltipTextView2.setVisibility(View.VISIBLE);
                calculateMortgage();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {
                tooltipTextView2.setVisibility(View.VISIBLE);
                calculateMortgage();}
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

        monthlyPaymentTextView.setText(format("Monthly Payment: £%.2f K", monthlyPayment));
     totalAmountTextView.setText(format("Total Amount: £%.2f K", totalAmount));

        InterestRateTextView = findViewById(R.id.InterestRateTextView);

        InterestRateTextView.setText(format("Rate of Interest : %s ", calculateInterestRate(depositAmount)));


    }
    private double calculateInterestRate(double depositAmount) {
        // Calculate interest rate based on deposit percentage
        double borrowAmount = borrowSeekBar.getProgress();

        if (depositAmount < 0.05 * borrowAmount) {
            return 0; // Deposits under 5% not permitted
        } else if (depositAmount < 0.1 * borrowAmount) {
            return 0.06; // Interest rate for 5-9% deposit
        } else if (depositAmount < 0.21 * borrowAmount) {
            return 0.04; // Interest rate for 10-20% deposit
        } else if (depositAmount < 0.31 * borrowAmount) {
            return 0.03; // Interest rate for 21-30% deposit
        } else {
            return 0.023; // Interest rate for 31%+ deposit
        }
    }
    private void saveMortgage() {
        // Get the mortgage details
        double borrowingAmount = borrowSeekBar.getProgress();
        double depositAmount = depositSeekBar.getProgress();
        double mortgageDuration = durationSeekBar.getProgress();

        String monthlyPaymentText = monthlyPaymentTextView.getText().toString();
        String totalAmountText = totalAmountTextView.getText().toString();
        String InterestRateText =   InterestRateTextView.getText().toString();
        monthlyPaymentText = monthlyPaymentText.replace("Monthly Payment: £", "");
        monthlyPaymentText=  monthlyPaymentText.replace(" K", "");
        totalAmountText = totalAmountText.replace("Total Amount: £", "");
        totalAmountText=  totalAmountText.replace(" K", "");
        InterestRateText = InterestRateText.replace("Rate of Interest : ", "");



        double monthlyPayment = Double.parseDouble(monthlyPaymentText);
        double totalAmount = Double.parseDouble(totalAmountText);
        double InterestRate = Double.parseDouble(InterestRateText);

        String userEmail = getIntent().getStringExtra("useremail");
        int currentUserId = dbAdapter.getUserIdByEmail(userEmail);
        String currentUserName = dbAdapter.getUserNameByEmail(userEmail);

        long result = dbAdapter.insertMortgage(currentUserId,currentUserName,borrowingAmount,depositAmount,mortgageDuration,monthlyPayment, totalAmount,InterestRate);

        if (result != -1) {
            Toast.makeText(this, "Mortgage calculation saved!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MortgageCalculatorActivity.this, MortgageResultActivity.class);
            intent.putExtra("currentUserName", currentUserName);
            intent.putExtra("borrowingAmount", borrowingAmount);
            intent.putExtra("depositAmount", depositAmount);
            intent.putExtra("mortgageDuration", mortgageDuration);
            intent.putExtra("monthlyPayment", monthlyPayment);
            intent.putExtra("totalAmount", totalAmount);
            intent.putExtra("InterestRate", InterestRate);

            intent.putExtra("useremail", userEmail);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed to save mortgage calculation!", Toast.LENGTH_SHORT).show();
        }
    }

}