package com.aliasadi.mvvm.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.aliasadi.mvvm.data.db.entity.LogClass;

import java.util.List;

/**
 * Created by Ali Asadi on 07/03/2018.
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
