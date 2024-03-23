package com.example.yogeshramakrishnan_android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter {
    DBHelper myHelper;
    public DBAdapter(Context context)
    {
        myHelper = new DBHelper(context);
    }
    static class DBHelper extends SQLiteOpenHelper {
        private static final String dbName = "MortgageDatabase";
        private static final String tableName = "UserRegister";
        private static final int dbVersion = 1;
        private static final String userId="_id";
        private static final String userName = "Name";
        private static final String userEmail = "Email";
        private static final String userPassword = "Password";
        private static final String userConfirmpassword = "ConfirmPassword";

        private static final String createTable = "CREATE TABLE "+tableName+" ("+userId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+userName+" VARCHAR(255) ,"+userEmail+" VARCHAR(225), "+userPassword+" VARCHAR(255), "+userConfirmpassword+" VARCHAR(255));";
        private static final String dropTable ="DROP TABLE IF EXISTS "+tableName;
        private final Context context;
        public DBHelper (Context context){
            super(context,dbName,null,dbVersion);
            this.context=context;
            SystemMessage.showMessage(context,"Database created");
        }
        public void onCreate (SQLiteDatabase dB){
            try {
                dB.execSQL(createTable);
            } catch (Exception e) {
                SystemMessage.showMessage(context,""+e);
            }
        }
        @Override
        public void onUpgrade (SQLiteDatabase dB,int oldVersion,int
                newVersion){
            try {
                dB.execSQL(dropTable);
                onCreate(dB);
            } catch (Exception e){
                SystemMessage.showMessage(context,""+e);
            }
        }
    }
    public long signUp(String fullName,String email, String password,String confirmpassword) {
        SQLiteDatabase dBase = myHelper.getWritableDatabase();
        ContentValues dataValues = new ContentValues();
        dataValues.put(myHelper.userName, fullName);
        dataValues.put(myHelper.userEmail, email);
        dataValues.put(myHelper.userPassword, password);
        dataValues.put(myHelper.userConfirmpassword, confirmpassword);
        return dBase.insert(myHelper.tableName, null, dataValues);
    }

    public long checkData(String email, String password) {
        SQLiteDatabase dBase = myHelper.getWritableDatabase();
        String[] dataColumn = {myHelper.userEmail, myHelper.userPassword};
        String[] recordArgs = {email, password}; // Pass both username and password as arguments
        long dataFound = 0;
        Cursor cursor = dBase.query(myHelper.tableName, null, myHelper.userEmail + " = ? AND " + myHelper.userPassword + " = ?", recordArgs, null, null, null);
        if (cursor.getCount() > 0) {
            dataFound = 1;
        }
        cursor.close();
        return dataFound;
    }

//    public String logIn(String username) {
//        SQLiteDatabase dBase = myHelper.getWritableDatabase();
//        String[] dataColumn = {myHelper.userName, myHelper.userPassword};
//        String[] recordArg = {username};
//        String userPassword = "";
//
//        Cursor cursor = dBase.query(myHelper.tableName, dataColumn, myHelper.userName + " = ?", recordArg, null, null, null);
//
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range")
//            String password = cursor.getString(cursor.getColumnIndex(myHelper.userPassword));
//            userPassword = password;
//        }
//
//        cursor.close();
//        return userPassword;
//    }




}
