package com.example.yogeshramakrishnan_android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private final DBHelper myHelper;
    private final SQLiteDatabase db;

    public DBAdapter(Context context) {
        myHelper = new DBHelper(context);
        db = myHelper.getWritableDatabase(); // Initialize SQLiteDatabase object
    }

    static class DBHelper extends SQLiteOpenHelper {
        private static final String dbName = "MortgageDatabase";
        private static final String tableName = "UserRegister";
        private static final String tableNameMortgage = "MortgageCalc";
        private static final int dbVersion = 1;
        private static final String userId = "_id";
        private static final String userName = "Name";
        private static final String userEmail = "Email";
        private static final String userPassword = "Password";
        private static final String userConfirmPassword = "ConfirmPassword";

        private static final String borrowAmount = "borrowAmount";
        private static final String initialDeposit = "initialDeposit";
        private static final String durationYear = "durationYear";
        private static final String monthlyPay = "monthlyPay";
        private static final String totalPay = "totalPay";

        private static final String createTable = "CREATE TABLE " + tableName + " (" + userId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + userName + " VARCHAR(100), " + userEmail + " VARCHAR(50), " + userPassword + " VARCHAR(50), " + userConfirmPassword + " VARCHAR(50));";
        private static final String createTableMortage = "CREATE TABLE " + tableNameMortgage + " (" + userId + " INTEGER, " + userName + " VARCHAR(100), " + borrowAmount + " Double, " + initialDeposit + " Double, " + durationYear + " Double, " + monthlyPay + " Double, " + totalPay + " Double);";
        private static final String dropTable = "DROP TABLE IF EXISTS " + tableName;

        private final Context context;

        public DBHelper(Context context) {
            super(context, dbName, null, dbVersion);
            this.context = context;
            SystemMessage.showMessage(context, "Database created");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(createTable);
                db.execSQL(createTableMortage);
            } catch (Exception e) {
                SystemMessage.showMessage(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(dropTable);
                onCreate(db);
            } catch (Exception e) {
                SystemMessage.showMessage(context, "" + e);
            }
        }
    }


    public long insertMortgage(int currentUserId, String currentUserName, double borrowingAmount, double depositAmount, double mortgageDuration, double monthlyPayment, double totalAmount) {
        SQLiteDatabase dBase = myHelper.getWritableDatabase();
        ContentValues dataValues = new ContentValues();
        dataValues.put(DBHelper.userId, currentUserId);
        dataValues.put(DBHelper.userName, currentUserName);
        dataValues.put(DBHelper.borrowAmount, borrowingAmount);
        dataValues.put(DBHelper.initialDeposit, depositAmount);
        dataValues.put(DBHelper.durationYear, mortgageDuration);
        dataValues.put(DBHelper.monthlyPay, monthlyPayment);
        dataValues.put(DBHelper.totalPay, totalAmount);
        return dBase.insert(DBHelper.tableNameMortgage, null, dataValues);
    }
    public long signUp(String fullName,String email, String password,String confirmpassword) {
        SQLiteDatabase dBase = myHelper.getWritableDatabase();
        ContentValues dataValues = new ContentValues();
        dataValues.put(DBHelper.userName, fullName);
        dataValues.put(DBHelper.userEmail, email);
        dataValues.put(DBHelper.userPassword, password);
        dataValues.put(DBHelper.userConfirmPassword, confirmpassword);
        return dBase.insert(DBHelper.tableName, null, dataValues);
    }

    public long checkData(String email, String password) {
        SQLiteDatabase dBase = myHelper.getWritableDatabase();
        String[] dataColumn = {DBHelper.userEmail, DBHelper.userPassword};
        String[] recordArgs = {email, password}; // Pass both username and password as arguments
        long dataFound = 0;
        Cursor cursor = dBase.query(DBHelper.tableName, null, DBHelper.userEmail + " = ? AND " + DBHelper.userPassword + " = ?", recordArgs, null, null, null);
        if (cursor.getCount() > 0) {
            dataFound = 1;
        }
        cursor.close();
        return dataFound;
    }

    // Get user ID by email
    public int getUserIdByEmail(String email) {
        int userId = -1;
        String[] columns = {"_id"};
        String selection = "Email" + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("UserRegister", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            cursor.close();
        }
        return userId;
    }

    // Get user name by email
    public String getUserNameByEmail(String email) {
        String userName = null;
        String[] columns = {"Name"};
        String selection = "Email" + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query("UserRegister", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            cursor.close();
        }
        return userName;
    }


}
