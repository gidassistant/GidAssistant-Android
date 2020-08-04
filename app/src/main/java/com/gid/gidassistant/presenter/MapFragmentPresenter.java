package com.gid.gidassistant.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.presenter.contracts.MapFragmentMainContract;
import com.gid.gidassistant.utils.Permissions;

public class MapFragmentPresenter implements MapFragmentMainContract.Presenter {

    private MapFragmentMainContract.View view;
    private static final String[] mapPermissions = new String[]
            {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
    private static final int REQUEST_PERMISSIONS_CODE = 101;

    public MapFragmentPresenter(MapFragmentMainContract.View view) {
        this.view = view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    view.mapAccepted(true);
                } else {
                    view.mapAccepted(false);
                }
            }
        }
    }

    @Override
    public void onCreate(Activity activity) {
        requestPermissions(activity);
    }

    @Override
    public void onDestroy(Activity view) {

    }

    @Override
    public void requestPermissions(Activity activity) {
        if (!Permissions.Checker.isForegroundLocationEnable(activity)) {
            ActivityCompat.requestPermissions (
                    activity,
                    permissions,
                    REQUEST_PERMISSIONS_CODE
            );
        } else{
            view.mapAccepted(true);
        }
    }
}
