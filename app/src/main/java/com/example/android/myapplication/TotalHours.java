package com.example.android.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.myapplication.data.ClockContract.ClockEntry;
import com.example.android.myapplication.data.ClockDbHelper;

import java.util.Locale;


public class TotalHours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_hours);
        displayDatabaseInfo();


    }




 private void displayDatabaseInfo () {
     //Create a helper object using the ClockDbHelper class
    ClockDbHelper helper = new ClockDbHelper(this);
    //Create a database object using the helper class object
    SQLiteDatabase database = helper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ClockEntry.COLUMN_DATE,
                ClockEntry.COLUMN_TIME_IN,
                ClockEntry.COLUMN_TIME_OUT,
                ClockEntry.COLUMN_TIME_FOR_DAY};

    // Perform a query on the clock table
    Cursor cursor = database.query(
            ClockEntry.TABLE_NAME,   // The table to query
            projection,            // The columns to return
            null,                  // The columns for the WHERE clause
            null,                  // The values for the WHERE clause
            null,                  // Don't group the rows
            null,                  // Don't filter by row groups
            null);                   // The sort order

try {

    // Figure out the index of each column
    int dateColumnIndex = cursor.getColumnIndex(ClockEntry.COLUMN_DATE);
    int timeInColumnIndex = cursor.getColumnIndex(ClockEntry.COLUMN_TIME_IN);
    int timeOutColumnIndex = cursor.getColumnIndex(ClockEntry.COLUMN_TIME_OUT);
    int daysHoursColumnIndex = cursor.getColumnIndex(ClockEntry.COLUMN_TIME_FOR_DAY);

    // Iterate through all the returned rows in the cursor
    while (cursor.moveToNext()) {
        // Use that index to extract the String or Int value of the word
        // at the current row the cursor is on.
        String currentDate = cursor.getString(dateColumnIndex);
        String currentTimeIn = cursor.getString(timeInColumnIndex);
        String currentTimeOut = cursor.getString(timeOutColumnIndex);
        int minutes = cursor.getInt(daysHoursColumnIndex);
        Log.v("TotalHours", "I ran");
        String currentHours = printHours(minutes);

        TextView displayView = (TextView) findViewById(R.id.logged_hours);
        // Display the values from each column of the current row in the cursor in the TextView
        displayView.append(("\n" +
                currentDate + " - " +
                currentTimeIn + " - " +
                currentTimeOut + " - " +
                currentHours));
    }
} finally {
    // Always close the cursor when you're done reading from it. This releases all its
    // resources and makes it invalid.
    cursor.close();
}


    }
    //Calculate current hours from minutes
    private String printHours (int minutes){
        float daysTime;
        daysTime = (float) minutes/60;
        String stringTimeFormatted = String.format(Locale.US, "%.2f", daysTime);
        Log.v("TotalHours",stringTimeFormatted);
        return stringTimeFormatted;

    }

}
