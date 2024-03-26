package com.example.yogeshramakrishnan_android;
import android.os.Bundle;
public class MortgageOption {
    private final double amountBorrowed;
    private final double deposit;
    private final int years;
    private final double monthlyPayment;
    private final double totalAmount;

    public MortgageOption(double amountBorrowed, double deposit, int years, double monthlyPayment, double totalAmount) {
        this.amountBorrowed = amountBorrowed;
        this.deposit = deposit;
        this.years = years;
        this.monthlyPayment = monthlyPayment;
        this.totalAmount = totalAmount;
    }

    public double getAmountBorrowed() {
        return amountBorrowed;
    }

    public double getDeposit() {
        return deposit;
    }

    public int getYears() {
        return years;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
