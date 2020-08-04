package com.gid.gidassistant.config;

import android.app.Application;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.gid.gidassistant.model.repository.interests.InterestsProvider;
import com.gid.gidassistant.model.repository.interests.InterestsHttpProvider;
import com.gid.gidassistant.model.repository.user.AppDatabase;

/**
 * Класс поставщик, для реализации внедрения зависимостей.
 */
public class App extends Application {

    private static InterestsProvider interestsProvider;
    public static App instance;

    private AppDatabase database;


    public static InterestsProvider getInterestsProvider(Context context) {
        if (interestsProvider == null)
            interestsProvider = new InterestsHttpProvider(context);
        return interestsProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static App getInstance() {
        if(instance == null)
            instance = new App();
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
