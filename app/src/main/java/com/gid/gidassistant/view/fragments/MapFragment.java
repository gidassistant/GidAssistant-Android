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

public class MapFragment extends Fragment implements OnMapReadyCallback, MapFragmentMainContract.View, Permissions.PermissionsObserver {

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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //checkPermission();
        interestsButton = this.view.findViewById(R.id.interestsButton);
        linearLayout = this.view.findViewById(R.id.interest_bottom_sheet);
        Log.d(TAG, "onViewCreated: " + linearLayout);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        interestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        checkPermission();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (isLocationEnable) {
            mMap.setMyLocationEnabled(true);

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latlng);

                    markerOptions.title("My Marker");
                    mMap.clear();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
                    mMap.animateCamera(cameraUpdate);
                    mMap.addMarker(markerOptions);
                }
            });
        }

    }

    private void checkPermission() {
        Permissions.Provider provider = new Permissions.Provider(this);
        provider.requestForegroundLocationPermission(getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onRequestPermissionGranted(String[] permissions) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.d(TAG, "onRequestPermissionsResult: " + mapFragment);
        mapFragment.getMapAsync(this);
        isLocationEnable = true;
    }

    @Override
    public void onRequestPermissionDenied(String[] permissions) {

    }
}