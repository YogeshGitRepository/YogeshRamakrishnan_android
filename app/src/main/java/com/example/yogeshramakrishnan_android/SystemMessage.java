package com.example.yogeshramakrishnan_android;

import android.content.Context;
import android.widget.Toast;

public class SystemMessage {
    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
