package com.example.yogeshramakrishnan_android;
public class FinancialDataMain {
    private final double takeHomeWage;
    private final double fixedOutgoings;
    private final double currentRentOrMortgage;
    private final double leftoverWage;
    private final double defaultBorrowAmount;
    private final double defaultDepositAmount;

        public FinancialDataMain(double takeHomeWage, double fixedOutgoings, double currentRentOrMortgage, double leftoverWage, double defaultBorrowAmount, double defaultDepositAmount) {
            this.takeHomeWage = takeHomeWage;
            this.fixedOutgoings = fixedOutgoings;
            this.currentRentOrMortgage = currentRentOrMortgage;
            this.leftoverWage = leftoverWage;
            this.defaultBorrowAmount = defaultBorrowAmount;
            this.defaultDepositAmount = defaultDepositAmount;
        }

        public double getTakeHomeWage() {
            return takeHomeWage;
        }

        public double getFixedOutgoings() {
            return fixedOutgoings;
        }

        public double getCurrentRentOrMortgage() {
            return currentRentOrMortgage;
        }

        public double getLeftoverWage() {
            return leftoverWage;
        }

        public double getDefaultBorrowAmount() {
            return defaultBorrowAmount;
        }

        public double getDefaultDepositAmount() {
            return defaultDepositAmount;
        }
    }


