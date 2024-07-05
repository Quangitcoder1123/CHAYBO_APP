package in.project.Activelife.countstep;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

public class StepsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StepsDatabase";
    private static final String TABLE_STEPS_SUMMARY = "StepsSummary";
    private static final String ID = "id";

    private static final String STEPS_COUNT = "stepscount";
    private static final String CREATION_DATE = "creationdate";//Date format is mm/dd/yyyy

    private SharedPreferences sharedPreferences;


    private static final String CREATE_TABLE_STEPS_SUMMARY = "CREATE TABLE "
            + TABLE_STEPS_SUMMARY + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CREATION_DATE + " TEXT," + STEPS_COUNT + " INTEGER" + ")";
    public StepsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STEPS_SUMMARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("Range")
    public boolean createStepsEntry() {
        boolean isDateAlreadyPresent = false;
        boolean createSuccessful = false;
        int currentDateStepCounts = 0;

        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat fomat=new SimpleDateFormat("MM/dd/yyyy");
        String todayDate =fomat.format(mCalendar);
        String selectQuery = "SELECT " + STEPS_COUNT + " FROM "
                + TABLE_STEPS_SUMMARY + " WHERE " + CREATION_DATE + " = '"+ todayDate+"' ";
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    isDateAlreadyPresent = true;
                    currentDateStepCounts =
                            c.getInt((c.getColumnIndex(STEPS_COUNT)));
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CREATION_DATE, todayDate);
            if (isDateAlreadyPresent) {
                values.put(STEPS_COUNT, ++currentDateStepCounts);
                int row = db.update(TABLE_STEPS_SUMMARY, values,
                        CREATION_DATE + " = '" + todayDate + "'", null);
                if (row == 1) {
                    createSuccessful = true;
                }
                db.close();
            } else {
                values.put(STEPS_COUNT, 1);

                long row = db.insert(TABLE_STEPS_SUMMARY, null,
                        values);
                if (row != -1) {

                    createSuccessful = true;
                }
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createSuccessful;
    }
    @SuppressLint("Range")
    public boolean createStepsEntry(int step) {
        boolean isDateAlreadyPresent = false;
        boolean createSuccessful = false;
        int currentDateStepCounts = 0;
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat fomat=new SimpleDateFormat("MM/dd/yyyy");
        String todayDate =fomat.format(mCalendar);
        String selectQuery = "SELECT " + STEPS_COUNT + " FROM "
                + TABLE_STEPS_SUMMARY + " WHERE " + CREATION_DATE + " = '"+ todayDate+"' ";
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    isDateAlreadyPresent = true;
                    currentDateStepCounts =
                            c.getInt((c.getColumnIndex(STEPS_COUNT)));
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CREATION_DATE, todayDate);
            if (isDateAlreadyPresent) {
                values.put(STEPS_COUNT, currentDateStepCounts+step);
                int row = db.update(TABLE_STEPS_SUMMARY, values,
                        CREATION_DATE + " = '" + todayDate + "'", null);
                if (row == 1) {
                    createSuccessful = true;
                }
                db.close();
            } else {
                values.put(STEPS_COUNT, 1);
                long row = db.insert(TABLE_STEPS_SUMMARY, null,
                        values);
                if (row != -1) {
                    createSuccessful = true;
                }
                db.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createSuccessful;
    }
    @SuppressLint("Range")
    public ArrayList<DateStepsModel> readStepsEntries()
    {
        ArrayList<DateStepsModel> mStepCountList = new ArrayList<DateStepsModel>();
        String selectQuery = "SELECT * FROM " + TABLE_STEPS_SUMMARY;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {
                    DateStepsModel mDateStepsModel = new DateStepsModel();
                    mDateStepsModel.mDate = c.getString((c.getColumnIndex(CREATION_DATE)));
                    mDateStepsModel.mStepCount = c.getInt((c.getColumnIndex(STEPS_COUNT)));
                    mStepCountList.add(mDateStepsModel);
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mStepCountList;
    }
    @SuppressLint("Range")
    public Integer readSteptoday()
    {
        int kq = 0;
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat fomat=new SimpleDateFormat("MM/dd/yyyy");
        String todayDate =fomat.format(mCalendar);
        String selectQuery ="SELECT " + STEPS_COUNT + " FROM "
                + TABLE_STEPS_SUMMARY + " WHERE " + CREATION_DATE + " ='" + todayDate + "'";
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            if (c.moveToFirst()) {
                do {

                    kq =c.getInt((c.getColumnIndex(STEPS_COUNT)));
                } while (c.moveToNext());
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}