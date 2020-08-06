package com.gid.gidassistant.config;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;


import com.gid.gidassistant.model.repository.interests.InterestsHttpProvider;
import com.gid.gidassistant.model.repository.user.AppDatabase;
import com.gid.gidassistant.utils.Utils;
import com.gid.gidassistant.view.activities.MainActivity;
import com.gid.gidassistant.view.activities.SplashActivity;

/**
 * Класс поставщик, для реализации внедрения зависимостей.
 */
public class App extends Application {

    public static App instance;

    private AppDatabase database;

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
