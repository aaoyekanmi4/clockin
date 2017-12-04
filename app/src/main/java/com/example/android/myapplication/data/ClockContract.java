package com.example.android.myapplication.data;

import android.provider.BaseColumns;
/**
 * Created by Arit on 11/30/2017.
 */

public final class ClockContract {
    private ClockContract() {}

    public static final class ClockEntry implements BaseColumns {
        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "clock";

        /**
         * Unique ID number for the clock in row (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Date of the clock in.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_DATE = "dayOf";

        /**
         * Time when clocked in.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_TIME_IN = "timeIn";

        /**
         * Time when clocked out.
         * <p>
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_TIME_OUT = "timeOut";

        /**
         * Minutes worked.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_TIME_FOR_DAY = "totalTime";
    }
}
