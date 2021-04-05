package com.aliasadi.mvvm.data;

import com.aliasadi.mvvm.data.repository.movie.MovieRepository;
import com.aliasadi.mvvm.data.repository.movie.MovieRepositoryImpl;
import com.aliasadi.mvvm.data.repository.movie.MovieCacheDataSource;
import com.aliasadi.mvvm.data.repository.movie.MovieLocalDataSource;
import com.aliasadi.mvvm.data.db.MovieDao;
import com.aliasadi.mvvm.data.db.MovieDatabase;
import com.aliasadi.mvvm.data.repository.movie.MovieRemoteDataSource;
import com.aliasadi.mvvm.data.api.MovieApi;
import com.aliasadi.mvvm.data.service.MovieService;
import com.preference.PowerPreference;
import com.preference.Preference;

/**
 * Created by Ali Asadi on 26/03/2018.
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

    public Preference getPrefs() {
        return PowerPreference.getDefaultFile();
    }

    public MovieRepository getMovieRepository() {

        MovieApi movieApi = MovieService.getInstance().getMovieApi();
        MovieRemoteDataSource movieRemote = MovieRemoteDataSource.getInstance(movieApi);

        MovieDao movieDao = MovieDatabase.getInstance().movieDao();
        MovieLocalDataSource movieLocal = MovieLocalDataSource.getInstance(movieDao);

        MovieCacheDataSource movieCache = MovieCacheDataSource.getsInstance();

        return MovieRepositoryImpl.getInstance(movieRemote,movieLocal,movieCache);
    }

}
