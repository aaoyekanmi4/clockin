package com.example.android.myapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.myapplication.data.ClockContract;
import com.example.android.myapplication.data.ClockDbHelper;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.name;
import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String weekday = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];
        TextView weeklySummary = (TextView) findViewById(R.id.summary);
        weeklySummary.setText(weekday + " " + month + "/" + day);




    }
    String inTime;
    String inTimeDisplayed;
    int databaseMinutes;
    public void clockInMessage(View view) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String stringRepresentation = sdf.format(date);




        inTimeDisplayed = stringRepresentation;
        Button in = (Button) findViewById(R.id.clock_in_button);
        Button out = (Button) findViewById(R.id.clock_out_button);

        TextView clockInTime = (TextView) findViewById(R.id.clock_in_time);
        clockInTime.setText("Clock in: " + inTimeDisplayed);
        in.setVisibility(View.GONE);
        out.setVisibility(View.VISIBLE);
        Date wholeDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
        String dateString = df.format(wholeDate);
        inTime = dateString;




    }

    String outTime;
    String outTimeDisplayed;
    String databaseDate;
    public void clockOutMessage(View view) throws ParseException {


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String stringRepresentation = sdf.format(date);
        outTimeDisplayed = stringRepresentation;


        TextView clockOutTime = (TextView) findViewById(R.id.clock_out_time);
        clockOutTime.setText("Clock out: " + outTimeDisplayed);
        Date wholeDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
        String dateString = df.format(wholeDate);
        outTime = dateString;

        Date forDatabase = new Date();
        SimpleDateFormat dayMonth = new SimpleDateFormat("MM.dd");
        String dayMonthString = dayMonth.format(forDatabase);
        databaseDate = dayMonthString;

        Date date1 = df.parse(inTime);
        Date date2 = df.parse(outTime);

        long inMilli  = date1.getTime();
        long outMilli = date2.getTime();

        long diff = outMilli - inMilli;



        long timeInSeconds = diff / 1000;
        Log.v("MainActivity", "" + timeInSeconds);
        long minutes;
        minutes = timeInSeconds / 60;
        databaseMinutes = toIntExact(minutes);
insertTime();

        long hours;
        hours = minutes /60;
        if (hours >= 1) {
            minutes = hours % 60;
        }
        TextView dailyTotal = (TextView) findViewById(R.id.daily_total);
        dailyTotal.setText("Time worked: " + hours + " hours and " + minutes + " minutes");
        Button out = (Button) findViewById(R.id.clock_out_button);
        out.setVisibility(View.GONE);



    }
    public void openHoursToDate(View view){
        Intent totalHours = new Intent (this, TotalHours.class);
        startActivity(totalHours);

        }

    private void insertTime(){
        // Create database helper
        ClockDbHelper helper = new ClockDbHelper(this);
//
//        // Gets the database in write mode
        SQLiteDatabase database = helper.getWritableDatabase();


        //Manual Entries for last pay period
        ContentValues manValues = new ContentValues();
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "1/31");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "9:45AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 465);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/1");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "4:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "7;00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 180);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/2");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "1:15AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "9:15AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 480);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/3");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "10:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 450);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/5");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "3:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "7:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 240);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/7");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "8:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 390);
        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/9");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "1:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "9:15AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 465);

        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/10");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:45AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "9:30AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 405);

        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/11");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:35AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "10:45AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 495);

        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);

        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "2/12");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "3:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "8:00AM");
        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 300);

        //insert values into the database
        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
//
//        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "1/27");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "2:30AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "8:40AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 370);
//
//        //insert values into the database
//        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
//
//
//        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "1/28");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "5:30AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "11:30AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 360);
//
//        //insert values into the database
//        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);
//
//        manValues.put(ClockContract.ClockEntry.COLUMN_DATE, "1/29");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_IN, "3:00AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, "8:30AM");
//        manValues.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, 330);
//
//        //insert values into the database
//        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, manValues);



        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//        values.put(ClockContract.ClockEntry.COLUMN_DATE, databaseDate);
//        values.put(ClockContract.ClockEntry.COLUMN_TIME_IN, inTimeDisplayed);
//        values.put(ClockContract.ClockEntry.COLUMN_TIME_OUT, outTimeDisplayed);
//        values.put(ClockContract.ClockEntry.COLUMN_TIME_FOR_DAY, databaseMinutes);
//
//        //insert values into the database
//        database.insert(ClockContract.ClockEntry.TABLE_NAME, null, values);
//        Log.v("MainActivity", "inserted content");

    }





}




