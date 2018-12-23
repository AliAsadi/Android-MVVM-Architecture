package com.example.ali.androidmvvm.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.ali.androidmvvm.data.db.entity.LogClass;

import java.util.List;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Dao
public interface LogDAO {

    @Query("SELECT * FROM LogClass")
    List<LogClass> getAll();

    @Query("DELETE FROM LogClass")
    void dropTable();

    @Insert
    void insertAll(LogClass... logs);

    @Delete
    void delete(LogClass log);
}
