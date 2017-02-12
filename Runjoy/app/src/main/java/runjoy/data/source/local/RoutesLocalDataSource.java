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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import runjoy.data.City;
import runjoy.data.Route;
import runjoy.data.RunInfo;
import runjoy.data.source.RouteDataSource;
import runjoy.data.source.local.RoutesPersistenceContract.RouteEntry;
import runjoy.data.source.local.RoutesPersistenceContract.RunEntry;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Concrete implementation of a data source as a db.
 */
public class RoutesLocalDataSource implements RouteDataSource {

    private static RoutesLocalDataSource INSTANCE;

    private RouteDbHelper mDbHelper;

    // Prevent direct instantiation.
    private RoutesLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new RouteDbHelper(context);
    }

    public static RoutesLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RoutesLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getLastRun(@NonNull GetRunCallback callback) {
        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String [] projection={
                RunEntry.COLUMN_NAME_DISTANCE,
                RunEntry.COLUMN_NAME_TIME,
                RunEntry.COLUMN_NAME_MISSIONNUM,
                RunEntry.COLUMN_NAME_ADDDISTANCE,
                RunEntry.COLUMN_NAME_MESSAGE,
                RunEntry.COLUMN_NAME_DATE
        };

        Cursor c = db.rawQuery("select * from run where date in (select max(date) from date)",null);

        RunInfo runInfo=null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            double distance=c.getDouble(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_DISTANCE));
            long time = c.getLong(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_TIME));
            int num = c.getInt(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_MISSIONNUM));
            Double addDistance =
                    c.getDouble(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_ADDDISTANCE));
            String message =
                    c.getString(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_MESSAGE));
            Long mdate=c.getLong(c.getColumnIndexOrThrow(RunEntry.COLUMN_NAME_DATE));
            Date date = new Date(mdate);
            runInfo = new RunInfo(distance,time, num, addDistance, message,date);
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (runInfo != null) {
            callback.onRunLoaded(runInfo);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void getMyRoute(@NonNull GetRouteCallback callback) {
        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        String [] projection={
                RouteEntry.COLUMN_NAME_START,
                RouteEntry.COLUMN_NAME_END,
                RouteEntry.COLUMN_NAME_ALLDISTANCE,
                RouteEntry.COLUMN_NAME_DISTANCE,
                RouteEntry.COLUMN_NAME_TIME
        };

        String selection = RouteEntry.COLUMN_NAME_COMPLETE+" LIKE ?";
        String [] selectionArgs={"0"};

        Cursor c = db.query(
                RouteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Route route=null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String id=c.getString(c.getColumnIndexOrThrow(RouteEntry._ID));
            String start = c.getString(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_START));
            String end = c.getString(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_END));
            Double allDistance =
                    c.getDouble(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_ALLDISTANCE));
            Double distance =
                    c.getDouble(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_DISTANCE));
            Long time=c.getLong(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_TIME));
            route = new Route(id,start, end, allDistance, distance,time);
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (route != null) {
            callback.onRouteLoaded(route);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public City getHistoryRoute() {
        SQLiteDatabase db=mDbHelper.getReadableDatabase();

        City city=null;

        String [] projection={
                RouteEntry.COLUMN_NAME_START,
                RouteEntry.COLUMN_NAME_END,
                RouteEntry.COLUMN_NAME_TIME
        };

        String selection = RouteEntry.COLUMN_NAME_COMPLETE+" LIKE ?";
        String [] selectionArgs={"1"};

        Cursor c = db.query(
                RouteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Map<String,String> route=new LinkedHashMap<String,String>();
        List<Long> time=new LinkedList<Long>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++) {
                c.move(i);
                String start = c.getString(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_START));
                String end = c.getString(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_END));
                Long mtime=c.getLong(c.getColumnIndexOrThrow(RouteEntry.COLUMN_NAME_TIME));
                route.put(start,end);
                time.add(mtime);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();
        city=new City(route,time);
        return city;
    }

    @Override
    public void saveRun(@NonNull RunInfo runInfo) {
        checkNotNull(runInfo);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RunEntry.COLUMN_NAME_DISTANCE,runInfo.getDistance());
        values.put(RunEntry.COLUMN_NAME_TIME,runInfo.getTime());
        values.put(RunEntry.COLUMN_NAME_MISSIONNUM,runInfo.getMissionNum());
        values.put(RunEntry.COLUMN_NAME_ADDDISTANCE,runInfo.getAddDistance());
        values.put(RunEntry.COLUMN_NAME_MESSAGE,runInfo.getMessage());
        values.put(RunEntry.COLUMN_NAME_DATE,runInfo.getDate().getTime());

        db.insert(RunEntry.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void updateRoute(@NonNull Route route) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RouteEntry.COLUMN_NAME_DISTANCE, route.getDistance());
        values.put(RouteEntry.COLUMN_NAME_TIME, route.getTime());

        String selection = RouteEntry._ID + " LIKE ?";
        String[] selectionArgs = { route.getId() };

        db.update(RouteEntry.TABLE_NAME, values, selection, selectionArgs);

        db.close();
    }

    @Override
    public void saveRoute(@NonNull Route route) {
        checkNotNull(route);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RouteEntry.COLUMN_NAME_START,route.getStart());
        values.put(RouteEntry.COLUMN_NAME_END,route.getEnd());
        values.put(RouteEntry.COLUMN_NAME_ALLDISTANCE,route.getAllDistance());
        values.put(RouteEntry.COLUMN_NAME_DISTANCE,route.getDistance());
        values.put(RouteEntry.COLUMN_NAME_TIME,route.getTime());

        db.insert(RouteEntry.TABLE_NAME, null, values);

        db.close();
    }
}