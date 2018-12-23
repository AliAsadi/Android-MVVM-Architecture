package com.example.ali.androidmvvm.data.prefs;

import com.example.ali.androidmvvm.utils.AppConstants;
import com.preference.PowerPreference;
import com.preference.utils.PreferenceCreator;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class Prefs {

    public static PreferenceCreator getInstance() {
        return PowerPreference.name(AppConstants.SP_NAME);
    }

}