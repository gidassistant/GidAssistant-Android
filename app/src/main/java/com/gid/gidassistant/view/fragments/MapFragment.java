package com.gid.gidassistant.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.gid.gidassistant.R;
import com.gid.gidassistant.presenter.MapFragmentPresenter;
import com.gid.gidassistant.presenter.contracts.MapFragmentMainContract;
import com.gid.gidassistant.utils.Permissions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapFragmentMainContract.View {

    private Context context;
    private View view;
    private GoogleMap mMap;
    private Activity activity;
    private static final String TAG = "MapFragment";
    private View interestsButton;
    private LinearLayout linearLayout;

    private boolean isLocationEnable = false;

    private MapFragmentMainContract.Presenter presenter;

    public MapFragment() {
        presenter = new MapFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maps_tab, container, false);
        presenter.onCreate(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBottomSheet();
    }

    private void setBottomSheet() {
        interestsButton = this.view.findViewById(R.id.interestsButton);
        linearLayout = this.view.findViewById(R.id.interest_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        interestsButton.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Log.d(TAG, "onMapReady: " + googleMap);
        Log.d(TAG, "onMapReady: " + isLocationEnable);

        if (isLocationEnable) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(location -> {
                LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latlng);

                markerOptions.title("My Marker");
                mMap.clear();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
                mMap.animateCamera(cameraUpdate);
                mMap.addMarker(markerOptions);
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void mapAccepted(boolean value) {
        isLocationEnable = value;
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.d(TAG, "onRequestPermissionsResult: " + mapFragment);
        mapFragment.getMapAsync(this);
    }
}