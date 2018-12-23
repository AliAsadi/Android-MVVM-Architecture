package com.example.ali.androidmvvm.data;

import com.example.ali.androidmvvm.App;
import com.example.ali.androidmvvm.data.db.database.LogDatabase;
import com.example.ali.androidmvvm.data.network.services.MovieService;
import com.preference.PowerPreference;
import com.preference.utils.PreferenceCreator;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public PreferenceCreator getPrefs() {
        return PowerPreference.defult();
    }

    public LogDatabase getLogDatabse() {
        return LogDatabase.getInstance(App.getInstance());
    }

    public MovieService getMovieService() {
        return new MovieService();
    }

}
