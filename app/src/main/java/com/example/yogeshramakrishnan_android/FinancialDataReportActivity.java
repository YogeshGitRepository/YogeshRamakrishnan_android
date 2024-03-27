package com.example.yogeshramakrishnan_android;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class FinancialDataReportActivity  extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FinancialDataAdapter adapter;
    private DBAdapter dbAdapter;
    private Button btnbackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_report_entry);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbAdapter = new DBAdapter(this);

        List<FinancialData> financialDataList = new ArrayList<>();

        // Retrieve data from the database
        Cursor cursor = dbAdapter.getAllFinancialData();
        if (cursor != null && cursor.moveToFirst()) {
            do {


                int takeHomeWageIndex = cursor.getColumnIndex("takeHomeWage");
                if (takeHomeWageIndex != -1) {
                    double takeHomeWage = cursor.getDouble(takeHomeWageIndex);
                    financialDataList.add(new FinancialData("Average Monthly Take Home Wage", takeHomeWage));
                }
                int fixedOutGoingsIndex = cursor.getColumnIndex("fixedOutGoings");
                if (fixedOutGoingsIndex != -1) {
                    double fixedOutgoings = cursor.getDouble(fixedOutGoingsIndex);
                    financialDataList.add(new FinancialData("Fixed Monthly Outgoings", fixedOutgoings));
                }
                int currentRentOrMortgageIndex = cursor.getColumnIndex("currentRentOrMortgage");
                if (currentRentOrMortgageIndex != -1) {
                    double currentRentOrMortgage = cursor.getDouble(currentRentOrMortgageIndex);
                    financialDataList.add(new FinancialData("Current Monthly Rent or Mortgage", currentRentOrMortgage));
                }
                int leftoverWageIndex = cursor.getColumnIndex("leftoverWage");
                if (leftoverWageIndex != -1) {
                    double leftoverWage = cursor.getDouble(leftoverWageIndex);
                    financialDataList.add(new FinancialData("Amount Leftover at the End of the Month", leftoverWage));
                }
                int defaultBorrowAmountIndex = cursor.getColumnIndex("defaultBorrowAmount");
                if (defaultBorrowAmountIndex != -1) {
                    double defaultBorrowAmount = cursor.getDouble(defaultBorrowAmountIndex);
                    financialDataList.add(new FinancialData("Default Borrow Amount", defaultBorrowAmount));
                }
                int defaultDepositAmountIndex = cursor.getColumnIndex("defaultDepositAmount");
                if (defaultDepositAmountIndex != -1) {
                    double defaultDepositAmount = cursor.getDouble(defaultDepositAmountIndex);
                    financialDataList.add(new FinancialData("Default Deposit Amount", defaultDepositAmount));
                }


            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No financial data found", Toast.LENGTH_SHORT).show();
        }

        adapter = new FinancialDataAdapter(financialDataList);
        recyclerView.setAdapter(adapter);

        btnbackHome = findViewById(R.id.backHome);
        btnbackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FinancialDataReportActivity.this, HomeActivity.class);
                String userEmail = getIntent().getStringExtra("useremail");
                intent.putExtra("useremail", userEmail);
                startActivity(intent);

            }
        });
    }
}
