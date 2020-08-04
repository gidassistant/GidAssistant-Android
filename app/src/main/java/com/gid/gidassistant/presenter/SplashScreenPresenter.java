package com.gid.gidassistant.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.ArraySet;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.config.App;
import com.gid.gidassistant.model.entities.User;
import com.gid.gidassistant.model.repository.user.UserRepository;
import com.gid.gidassistant.presenter.contracts.SplashScreenMainContract;
import com.gid.gidassistant.utils.Permissions;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SplashScreenPresenter implements SplashScreenMainContract.Presenter {

    private SplashScreenMainContract.View view;
    private SplashScreenMainContract.Model model;
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private Set<String> permissions = new TreeSet<>(Arrays.asList(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE));

    public SplashScreenPresenter(SplashScreenMainContract.View view) {
        this.view = view;
        this.model = new UserRepository();
    }

    @Override
    public void openServer() {

    }

    @Override
    public void addPermission(String... permission) {
        permissions.addAll(Arrays.asList(permission));
    }

    public void deletePermission(String... permission) {
        permissions.removeAll(Arrays.asList(permission));
    }

    @Override
    public void provideLocationPermissions(Activity activity) {
        if (!Permissions.Checker.isForegroundLocationEnable(activity)) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSIONS_REQUEST_CODE
            );
        }
    }

    public void provideSelectedPermission(Activity activity) {
        String[] permission = permissions.toArray(new String[0]);
        ActivityCompat.requestPermissions(
                activity,
                permission,
                PERMISSIONS_REQUEST_CODE
        );
    }

    @Override
    public void provideStoragePermissions(Activity activity) {
        if (!Permissions.Checker.isReadExternalStorageEnable(activity)) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    PERMISSIONS_REQUEST_CODE
            );
        }
    }

    @Override
    public boolean isFirstRun() {
        boolean isUserPresent = model.isUserPresent(0);
        if(!isUserPresent)
            model.addUser(new User());
        return !isUserPresent;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /*switch(requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    observer.processFinished(true, permissions);
                } else {
                    observer.processFinished(false, permissions);
                }
        }*/
    }
}
