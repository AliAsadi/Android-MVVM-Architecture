package com.aliasadi.mvvm.data.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.WorkerThread;
import com.aliasadi.mvvm.data.db.dao.LogDAO;
import com.aliasadi.mvvm.data.db.entity.LogClass;

/**
 * Created by Ali Asadi on 07/03/2018.
 */

@Database(entities = {LogClass.class}, version = 2, exportSchema = false)
public abstract class LogDatabase extends RoomDatabase {

    private static LogDatabase sInstance;

    @WorkerThread
    public abstract LogDAO logDao();

    private static LogDatabase initialize(Context context) {
        sInstance = Room.databaseBuilder(context.getApplicationContext(), LogDatabase.class, "log-database").fallbackToDestructiveMigration().build();
        return sInstance;
    }

    public static LogDatabase getInstance(Context context) {
        if (sInstance == null) {
            return initialize(context);
        } else {
            return sInstance;
        }
    }

    public static void destroyInstance() {
        sInstance = null;
    }

    public static void addLog(final LogDatabase db, final LogClass log) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                db.logDao().insertAll(log);

            }
        };
        thread.start();
    }


    public static void dropTable(LogDatabase db) {
        db.logDao().dropTable();
    }
}
