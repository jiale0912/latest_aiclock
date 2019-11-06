//package com.example.aiclock.backup;
//
//import android.net.Uri;
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity(tableName = "alarm_table")
//public class Alarm {
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name = "id")
//    private int id;
//
//    @ColumnInfo(name="hour")
//    private int hour;
//    @ColumnInfo(name="min")
//    private int min;
//    @ColumnInfo(name="tips")
//    private String tips;
//    @ColumnInfo(name="week")
//    private int week;
//    @ColumnInfo(name="soundtrack")
//    private Uri soundtrack;
//    @ColumnInfo(name="soundorvibrator")
//    private int soundorvibrator;
//
//    public Alarm(@NonNull int id, int hour, int min, String tips, int week, Uri soundtrack, int soundorvibrator) {
//        this.id = id;
//        this.hour = hour;
//        this.min = min;
//        this.tips = tips;
//        this.week = week;
//        this.soundtrack = soundtrack;
//        this.soundorvibrator = soundorvibrator;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getHour() {
//        return hour;
//    }
//
//    public int getMin() {
//        return min;
//    }
//
//    public String getTips() {
//        return tips;
//    }
//
//    public int getWeek() {
//        return week;
//    }
//
//    public Uri getSoundtrack() {
//        return soundtrack;
//    }
//
//    public int getSoundorvibrator() {
//        return soundorvibrator;
//    }
//}
