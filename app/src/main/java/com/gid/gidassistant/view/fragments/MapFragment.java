package com.gid.gidassistant.view.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapFragmentMainContract.View {

    private Context context;
    private View view;
    private GoogleMap mMap;
    private Activity activity;
    private static final String TAG = "MapFragment";
    private View interestsButton;
    private LinearLayout linearLayout;
    private SupportMapFragment mapFragment;

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
        setBottomSheet(view);
    }

    private void setBottomSheet(View view) {
        interestsButton = view.findViewById(R.id.interestsButton);
        interestsButton.setOnClickListener(v -> {
            BottomInterestsFragment fragment = BottomInterestsFragment.newInstance();
            //linearLayout = view.findViewById(R.id.interest_bottom_sheet);
            //final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
            //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            fragment.show(getChildFragmentManager(), "InterestsBottomSheet");
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(this::animateCameraToMyLocation);
        if (isLocationEnable) {
            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            animateCameraToMyLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        }
        mMap.setOnMyLocationClickListener(this::animateCameraToMyLocation);
    }

    private void animateCameraToMyLocation(Location location) {
        if (isLocationEnable) {
            mMap.setMyLocationEnabled(true);
            if(location != null) {
                LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 17);
                mMap.animateCamera(cameraUpdate);
            }
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
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}