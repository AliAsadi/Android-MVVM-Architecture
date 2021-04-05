package com.aliasadi.mvvm.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.aliasadi.mvvm.App;
import com.aliasadi.mvvm.data.domain.Movie;
import com.aliasadi.mvvm.data.model.MovieRemote;

/**
 * Created by Ali Asadi on 30/01/2019.
 */
@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance() {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(App.getInstance(), MovieDatabase.class, "Movie.db").build();
        }
        return sInstance;
    }
}
