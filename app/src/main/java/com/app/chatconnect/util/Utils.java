package com.app.chatconnect.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Random;

public class Utils {

    public static int generateRandomNumber(int digits) {
        if (digits <= 0) {
            throw new IllegalArgumentException("Number of digits must be greater than zero.");
        }

        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void updateSharedPrefData(Context context, String KEY, int MODE, boolean DATA){
        SharedPreferences preferences = context.getSharedPreferences(KEY , MODE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor pref_edit = preferences.edit();
        pref_edit.putBoolean(KEY, DATA);
        pref_edit.apply();
    }

    public static void checkSharedPref(Context context, String KEY, int MODE, Class<?> FROM_CLASS, Class<?> TO_CLASS) {
        SharedPreferences preferences = context.getSharedPreferences(KEY, MODE);
        boolean isEndStartPage = preferences.getBoolean(KEY, false);
        if (isEndStartPage) {
            Intent intent = new Intent(context, TO_CLASS);
            context.startActivity(intent);

            //if(context instanceof android.app.Activity){
                ((Activity) context).finish();
            //}
        }
    }


}
