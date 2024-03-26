package com.example.yogeshramakrishnan_android;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MortgageOptionsActivity extends AppCompatActivity {

    private ListView listView;
    private MortgageOptionAdapter adapter;
    private ArrayList<MortgageOption> mortgageOptions= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_options);

        // Assuming you have retrieved mortgage options for the logged-in user
        // and stored them in the mortgageOptions ArrayList
        mortgageOptions = getMortgageOptionsForCurrentUser();

        listView = findViewById(R.id.listView);
        adapter = new MortgageOptionAdapter(this, R.layout.item_mortgage_option, mortgageOptions);
        listView.setAdapter(adapter);
    }

    // Example method to retrieve mortgage options for the currently logged-in user
    private ArrayList<MortgageOption> getMortgageOptionsForCurrentUser() {
        // Implement this method to retrieve mortgage options from the database
        // or any other source based on the currently logged-in user
        ArrayList<MortgageOption> options = new ArrayList<>();
        // Fetch mortgage options for the current user and populate the options ArrayList

        return options;
    }
}
