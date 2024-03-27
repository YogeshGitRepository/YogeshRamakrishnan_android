package com.example.yogeshramakrishnan_android;
// FinancialData.java
public class FinancialData {
    private final String label;
    private final double value;

    public FinancialData(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }
}
