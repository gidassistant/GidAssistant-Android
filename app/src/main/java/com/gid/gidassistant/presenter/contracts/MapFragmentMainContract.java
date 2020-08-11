package com.gid.gidassistant.presenter.contracts;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.model.entities.Interest;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public interface MapFragmentMainContract {

    interface View {
        void mapAccepted(boolean value);
    }

    interface Presenter extends ActivityCompat.OnRequestPermissionsResultCallback {
        String[] permissions = new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
        };
        void onCreate(Activity view);
        void onDestroy(Activity view);
        void requestPermissions(Activity activity);
        void loadInterestsList(Activity context, ChipGroup chipGroup);
    }

    interface Repository {
        List<Interest> getAllInterests(Context context);
    }
}
