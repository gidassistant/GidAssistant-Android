package com.gid.gidassistant.presenter.contracts;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.model.entities.User;

public interface SplashScreenMainContract {

    interface View {
        void openPrivacyPolice(android.view.View view);
        void openLicence(android.view.View view);
        void onLocationSwitched(android.view.View view);
        void onStorageSwitched(android.view.View view);
        void onNextButtonClick(android.view.View view);
    }

    interface Presenter extends ActivityCompat.OnRequestPermissionsResultCallback {
        void openServer();
        void addPermission(String... permission);
        void deletePermission(String... permission);
        void provideSelectedPermission(Activity activity);
        void provideLocationPermissions(Activity activity);
        void provideStoragePermissions(Activity activity);
        void startMainActivity(Activity context);
        boolean isFirstRun(Activity activity);
    }

    interface Model {
        boolean isUserExists(int id);
        void addUser(User user);
    }
}
