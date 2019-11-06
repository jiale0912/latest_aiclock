package com.example.aiclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class myDbAdapter {
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }



    public boolean insertData(int hour, int min, String tips, int week, int sov, String soundtrack) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.HOUR,hour);
        contentValues.put(myDbHelper.MIN,min);
        contentValues.put(myDbHelper.TIPS,tips);
        contentValues.put(myDbHelper.WEEK,week);
        contentValues.put(myDbHelper.SOV,sov);
        contentValues.put(myDbHelper.SOUNDTRACK,soundtrack);
        long result = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);

        return result != -1;

    }


    public void deleteall(Context context)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        try{
            db.execSQL("DELETE FROM "+ myDbHelper.TABLE_NAME);
        }catch(SQLiteException e)
        {
            e.printStackTrace();
        }
    }
private Alarm cursorToAlarm(Cursor cursor){
        Alarm alarm = new Alarm();
    alarm.setId(cursor.getInt(cursor.getColumnIndex(myDbHelper.UID)));
    alarm.setHour(cursor.getInt(cursor.getColumnIndex(myDbHelper.HOUR)));
    alarm.setMin(cursor.getInt(cursor.getColumnIndex(myDbHelper.MIN)));
    alarm.setTips(cursor.getString(cursor.getColumnIndex(myDbHelper.TIPS)));
    alarm.setSoundtrack(Uri.parse(cursor.getString(cursor.getColumnIndex(myDbHelper.SOUNDTRACK))));
    return alarm;
}

    public ArrayList<Alarm> getData()
        {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ArrayList<Alarm> mydata = new ArrayList<>();
        String QUERY = "SELECT * FROM "+ myDbHelper.TABLE_NAME;
        String[] columns = {myDbHelper.UID,myDbHelper.HOUR,myDbHelper.MIN,myDbHelper.TIPS,myDbHelper.SOUNDTRACK};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
       try
       {
           cursor.moveToFirst();
           while (!cursor.isAfterLast()) {
               Alarm alarm = cursorToAlarm(cursor);
               mydata.add(alarm);
               cursor.moveToNext();
           }
           cursor.close();
       }catch(Exception e)
       {
           e.printStackTrace();
       }


        return mydata;
    }

    public  int delete(String UID)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={UID};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.UID+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldHour , String newHour)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.HOUR,newHour);
        String[] whereArgs= {oldHour};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.HOUR+" = ?",whereArgs );
        return count;
    }


    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myAlarm";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String HOUR = "Hour";//Column II
        private static final String MIN = "Min";
        private static final String TIPS = "Tips";
        private static final String WEEK = "Week";
        private static final String SOV = "SoundOrVibrator";
        private static final String SOUNDTRACK = "SoundTrack";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HOUR + " INTEGER ," + MIN + " INTEGER, " + TIPS + " VARCHAR(255)" +
                "," + WEEK + " INTEGER," + SOV + " INTEGER," + SOUNDTRACK + " VARCHAR(255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "on upgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

