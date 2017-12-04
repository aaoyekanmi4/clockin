package com.example.android.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.myapplication.data.ClockContract.ClockEntry;

/**
 * Created by Arit on 11/30/2017.
 */

public class ClockDbHelper extends SQLiteOpenHelper {
    /** Name of the database file */
    private static final String DATABASE_NAME = "timeclock.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link ClockDbHelper}.
     *
     * @param context of the app
     */
    public ClockDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_CLOCK_TABLE =  "CREATE TABLE " + ClockEntry.TABLE_NAME + " ("
                + ClockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ClockEntry.COLUMN_DATE + " TEXT, "
                + ClockEntry.COLUMN_TIME_IN + " TEXT, "
                + ClockEntry.COLUMN_TIME_OUT + " TEXT, "
                + ClockEntry.COLUMN_TIME_FOR_DAY + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CLOCK_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
