package com.example.yogeshramakrishnan_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    DatabaseHelper myHelper;
    public DBAdapter(Context context)
    {
        myHelper = new DatabaseHelper(context);
    }
    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String dbName = "myDBName";
        private static final String tableName = "myTableName";
        private static final int dbVersion = 1;
        private static final String userId="_id";
        private static final String userName = "Name";
        private static final String userPassword = "Password";
        private static final String createTable = "CREATE TABLE "+tableName+" ("+userId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+userName+" VARCHAR(255) ,"+userPassword+" VARCHAR(225));";
        private static final String dropTable ="DROP TABLE IF EXISTS "+tableName;
        private final Context context;
        public DatabaseHelper (Context context){
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


}
