/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package runjoy.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class RouteDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 11;

    public static final String DATABASE_NAME = "Route.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String DOUBLE_TYPE = " DOUBLE";

    private static final String TIME_TYPE = " INTEGER";

    private static final String DATETYPE = " LONG";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + RoutesPersistenceContract.RouteEntry.TABLE_NAME + " (" +
                    RoutesPersistenceContract.RouteEntry._ID + BOOLEAN_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_START + TEXT_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_END + TEXT_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_ALLDISTANCE + DOUBLE_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_DISTANCE + DOUBLE_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_ARDISTANCE + BOOLEAN_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_TIME + TIME_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RouteEntry.COLUMN_NAME_COMPLETE + BOOLEAN_TYPE +
            " )";

    private static final String SQL_CREATE_RUN =
            "CREATE TABLE " + RoutesPersistenceContract.RunEntry.TABLE_NAME + " (" +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_DISTANCE + DOUBLE_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_TIME + TIME_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_MISSIONNUM + BOOLEAN_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_ADDDISTANCE + DOUBLE_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_ARDISTANCE + BOOLEAN_TYPE + COMMA_SEP +
                    RoutesPersistenceContract.RunEntry.COLUMN_NAME_DATE + DATETYPE +
                    " )";

    public RouteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_RUN);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + RoutesPersistenceContract.RouteEntry.TABLE_NAME );
        db.execSQL( "DROP TABLE IF EXISTS " + RoutesPersistenceContract.RunEntry.TABLE_NAME );
        onCreate(db);
        Log. i("Database！！！！！！！！！" ,"onUpgrade" );
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
