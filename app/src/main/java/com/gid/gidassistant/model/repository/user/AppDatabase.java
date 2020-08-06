package com.gid.gidassistant.model.repository.user;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gid.gidassistant.model.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
