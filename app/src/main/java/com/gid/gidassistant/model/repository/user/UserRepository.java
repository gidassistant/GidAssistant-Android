package com.gid.gidassistant.model.repository.user;

import android.content.SharedPreferences;
import android.util.Log;

import com.gid.gidassistant.config.App;
import com.gid.gidassistant.model.entities.User;
import com.gid.gidassistant.presenter.contracts.SplashScreenMainContract;

public class UserRepository implements SplashScreenMainContract.Model {

    private static final String TAG = "UserRepository";

    @Override
    public boolean isUserExists(int id) {
        User user = App.getInstance().getDatabase().userDao().getUser(id);
        Log.d(TAG, "isUserExists: " + (user != null));
        return user != null;
    }

    @Override
    public void addUser(User user) {
        App.getInstance().getDatabase().userDao().insert(user);
    }
}
