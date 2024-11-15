package com.app.chatconnect.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.chatconnect.models.User;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "ChatConnect";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USER = "users";
    private static final String USER_COL_ID = "id";
    private static final String USER_COL_FIRST_NAME= "fast_name";
    private static final String USER_COL_LAST_NAME = "last_name";
    private static final String USER_COL_EMAIL = "email";
    private static final String USER_COL_ADDRESS = "address";
    private static final String USER_COL_CONTACT = "contact";
    private static final String USER_COL_PASSWORD = "password";

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COL_FIRST_NAME + " VARCHAR(50) NOT NULL, " +
                USER_COL_LAST_NAME + " VARCHAR(50), " +
                USER_COL_EMAIL + " VARCHAR(100) UNIQUE NOT NULL, " +
                USER_COL_ADDRESS + " VARCHAR(255), " +
                USER_COL_CONTACT + " VARCHAR(15), " +
                USER_COL_PASSWORD + " VARCHAR(255) NOT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_COL_ID, user.getId());
        contentValues.put(USER_COL_FIRST_NAME, user.getFirstName());
        contentValues.put(USER_COL_LAST_NAME, user.getLastName());
        contentValues.put(USER_COL_EMAIL, user.getEmail());
        contentValues.put(USER_COL_ADDRESS, user.getAddress());
        contentValues.put(USER_COL_CONTACT, user.getContact());
        contentValues.put(USER_COL_PASSWORD, user.getPassword());
        long result = db.insert(TABLE_USER, null, contentValues);

        //db.close();
        return result != -1;
    }

    public User getUserByGmail(String email) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + USER_COL_EMAIL + " = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(USER_COL_ID)));
            user.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_FIRST_NAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_LAST_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_EMAIL)));
            user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_ADDRESS)));
            user.setContact(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_CONTACT)));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(USER_COL_PASSWORD)));
        }
        cursor.close();
        return user;
    }

    public boolean checkCredential(String gmail, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isValid = false;

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USER + " WHERE " + USER_COL_EMAIL + " = ? AND " + USER_COL_PASSWORD + " = ?",
                new String[]{gmail, password});

        if (cursor != null && cursor.moveToFirst()) {
            isValid = true;
        }

        if (cursor != null) {
            cursor.close();
        }

        return isValid;
    }


}
