package com.gid.gidassistant.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.R;
import com.gid.gidassistant.model.entities.Interest;
import com.gid.gidassistant.model.repository.interests.InterestsHttpProvider;
import com.gid.gidassistant.presenter.contracts.MapFragmentMainContract;
import com.gid.gidassistant.utils.Permissions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.List;


public class MapFragmentPresenter implements MapFragmentMainContract.Presenter {

    private static final String TAG = "MapFragmentPresenter";

    private MapFragmentMainContract.View view;
    private MapFragmentMainContract.Repository repository;
    private static final String[] mapPermissions = new String[]
            {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
    private static final int REQUEST_PERMISSIONS_CODE = 101;

    public MapFragmentPresenter(MapFragmentMainContract.View view) {
        this.view = view;
        this.repository = new InterestsHttpProvider(this);
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

    @Override
    public void loadInterestsList(Activity activity, ChipGroup chipGroup) {
        List<Interest> interestList = repository.getAllInterests();
        Log.d(TAG, "loadInterestsList: " + interestList.size());
        if(interestList != null && interestList.size() != 0) {
            for (int index = 0; index < interestList.size(); index++) {
                final Interest tagName = interestList.get(index);
                Chip chip = new Chip(activity);
                //Chip chip =  (Chip)activity.getLayoutInflater().inflate(R.layout.single_chip_layout, chipGroup, false);
                int paddingDp = 10;
                chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
                chip.setText(tagName.getName());
                chip.setCheckable(true);
                chip.setCheckedIconResource(R.drawable.ic_action_navigation_checked);
                //chip.setCloseIconResource(R.drawable.ic_action_navigation_close);
                //Added click listener on close icon to remove tag from ChipGroup
                chip.setOnCloseIconClickListener(v -> {
                    interestList.remove(tagName);
                    chipGroup.removeView(chip);
                });

                chipGroup.addView(chip);
            }
        }
    }
}
