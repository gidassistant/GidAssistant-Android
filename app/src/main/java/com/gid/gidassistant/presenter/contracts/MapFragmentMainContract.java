package com.gid.gidassistant.presenter.contracts;

import android.Manifest;
import android.app.Activity;

import androidx.core.app.ActivityCompat;

public interface MapFragmentMainContract {

    interface View {

    }

    interface Presenter extends ActivityCompat.OnRequestPermissionsResultCallback {
        String[] permissions = new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
        };
        void onCreate();
        void onDestroy();
        void requestPermissions(Activity activity);
    }

    interface Repository {

    }
}
