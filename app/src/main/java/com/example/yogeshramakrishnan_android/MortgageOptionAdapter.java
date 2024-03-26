package com.example.yogeshramakrishnan_android;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MortgageOptionAdapter extends ArrayAdapter<MortgageOption> {

    private final Context mContext;
    private final int mResource;

    public MortgageOptionAdapter(Context context, int resource, ArrayList<MortgageOption> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        MortgageOption option = getItem(position);

        TextView tvAmountBorrowed = convertView.findViewById(R.id.tvAmountBorrowed);
        TextView tvDeposit = convertView.findViewById(R.id.tvDeposit);
        TextView tvYears = convertView.findViewById(R.id.tvYears);
        TextView tvMonthlyPayment = convertView.findViewById(R.id.tvMonthlyPayment);
        TextView tvTotalAmount = convertView.findViewById(R.id.tvTotalAmount);

        // Populate the TextViews with mortgage option data
        tvAmountBorrowed.setText(String.format("Amount Borrowed: £%.2f", option.getAmountBorrowed()));
        tvDeposit.setText(String.format("Deposit: £%.2f", option.getDeposit()));
        tvYears.setText(String.format("Years: %d", option.getYears()));
        tvMonthlyPayment.setText(String.format("Monthly Payment: £%.2f", option.getMonthlyPayment()));
        tvTotalAmount.setText(String.format("Total Amount: £%.2f", option.getTotalAmount()));

        return convertView;
    }
}
