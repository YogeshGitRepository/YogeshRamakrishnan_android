package com.example.yogeshramakrishnan_android;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FinancialDataAdapter extends RecyclerView.Adapter<FinancialDataAdapter.ViewHolder> {

    private final List<FinancialData> financialDataList;

    public FinancialDataAdapter(List<FinancialData> financialDataList) {
        this.financialDataList = financialDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_financial_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinancialData financialData = financialDataList.get(position);
        holder.labelTextView.setText(financialData.getLabel());
        holder.valueTextView.setText(String.valueOf(financialData.getValue()));
    }

    @Override
    public int getItemCount() {
        return financialDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView labelTextView;
        TextView valueTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            labelTextView = itemView.findViewById(R.id.labelTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }
}
