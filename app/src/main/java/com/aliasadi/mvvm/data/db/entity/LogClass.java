package com.aliasadi.mvvm.data.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ali Asadi on 07/03/2018.
 */

@Entity
public class LogClass {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "Log")
    private String Log;

    @ColumnInfo(name = "Class")
    private String ClassName;

    @ColumnInfo(name = "Date")
    private String Date;

    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String className) {
        ClassName = className;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public String getLog() {
        return Log;
    }
    public void setLog(String log) {
        Log = log;
    }
}
