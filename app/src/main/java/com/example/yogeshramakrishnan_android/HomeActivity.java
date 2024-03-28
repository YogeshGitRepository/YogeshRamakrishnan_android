package com.example.yogeshramakrishnan_android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

        ToRetrieveData();
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
    public void ToRetrieveData() {
        Cursor cursor = dbAdapter.getAllMortgageData();
        if (cursor != null) {
            int monthlyPayIndex = cursor.getColumnIndex("monthlyPay");
            int totalPayIndex = cursor.getColumnIndex("totalPay");
            int interestRateIndex = cursor.getColumnIndex("interestRate");
            if (monthlyPayIndex != -1 && totalPayIndex != -1 && interestRateIndex != -1) {
                if (cursor.moveToFirst()) {
                    do {

                        double monthlyPay = cursor.getDouble(monthlyPayIndex);
                        double totalPay = cursor.getDouble(totalPayIndex);
                        double interestRate = cursor.getDouble(interestRateIndex);
                        TextView monthlyPaymentTextView = findViewById(R.id.textView2);
                        TextView totalAmountTextView = findViewById(R.id.textView3);
                        TextView InterestRateTextView= findViewById(R.id.textView4);
                        monthlyPaymentTextView.setText(String.format("Monthly Payment: £%.2f K", monthlyPay));
                        totalAmountTextView.setText(String.format("Total Amount: £%.2f K", totalPay));
                        InterestRateTextView.setText(String.format("Interest Rate : %s", (int)(interestRate *(100)) + " %"));

                    } while (cursor.moveToNext());
                }
            }
            cursor.close(); // Close the cursor after you're done with it to release resources
        }
    }
}
