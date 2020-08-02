package com.gid.gidassistant.config;

import android.content.Context;

import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.model.repository.interests.InterestsProvider;
import com.gid.gidassistant.model.repository.interests.InterestsHttpProvider;

/**
 * Класс поставщик, для реализации внедрения зависимостей.
 */
public class App {

    private static InterestsProvider interestsProvider;


    public static InterestsProvider getInterestsProvider(Context context) {
        if(interestsProvider == null)
            interestsProvider = new InterestsHttpProvider(context);
        return interestsProvider;
    }


}
