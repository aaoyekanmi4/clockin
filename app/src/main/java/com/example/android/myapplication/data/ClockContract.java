package com.example.android.myapplication.data;

import android.net.Uri;
import android.provider.BaseColumns;
/**
 * Created by Arit on 11/30/2017.
 */

public final class ClockContract {
    private ClockContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.myapplication";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CLOCK = "clock";

    public static final class ClockEntry implements BaseColumns {

        /** The content URI to access the clock data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CLOCK);
        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "clock";

        /**
         * Unique ID number for the clock in row (only for use in the database table).
         *
         * Type: INTEGER
         *
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
