package com.psilevelapp.psilevel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.psilevelapp.R;
import com.psilevelapp.model.PSILevelsModel;
import com.psilevelapp.psilevel.adapter.PSILevelAdapter;
import com.psilevelapp.psilevel.viewmodel.PSILevelViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap;

    private PSILevelViewModel viewModel;

    // It will have data of regions
    private HashMap<String, LatLng> hashMapRegions;
    // It will have data of readings of regions
    private HashMap<String, HashMap<String, Integer>> hashMapRegionsReading;

    private LinearLayoutManager mLayoutManager;
    private RecyclerView rclrvwPSILevels;
    private PSILevelAdapter adapter;
    // It will have data of readings of regions to populate in RecyclerView
    private ArrayList<String> regionReadings;

    // Used for animation
    private boolean isPSIReadingShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         */

        setupRecyclerView();
        getData();
    }

    /**
     * Set up of Recyclerview with adapter
     */
    private void setupRecyclerView() {
        rclrvwPSILevels = (RecyclerView) findViewById(R.id.rcyclvw);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rclrvwPSILevels.setLayoutManager(mLayoutManager);
        rclrvwPSILevels.setItemAnimator(new DefaultItemAnimator());
        rclrvwPSILevels.addItemDecoration(new DividerItemDecoration(rclrvwPSILevels.getContext(), mLayoutManager.getOrientation()));
        regionReadings = new ArrayList<>();
        adapter = new PSILevelAdapter(regionReadings);
        rclrvwPSILevels.setAdapter(adapter);
    }

    /**
     * extracted data from hashmap to populate in recycler view
     *
     * @param region
     * @return
     */
    private ArrayList<String> getPSILevelReading(String region) {
        regionReadings = new ArrayList<>();
        HashMap<String, Integer> regionReading = hashMapRegionsReading.get(region);
        Iterator iterator = regionReading.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            StringBuilder strBldr = new StringBuilder();
            strBldr.append(pair.getKey()).append("-").append(pair.getValue());
            regionReadings.add(strBldr.toString());
//            iterator.remove();
        }
        return regionReadings;
    }

    /**
     * API call to get PSI data
     */
    private void getData() {
        viewModel = new PSILevelViewModel();
        viewModel.getPSILevels(new Consumer<PSILevelsModel>() {
            @Override
            public void accept(PSILevelsModel psiLevelsModel) throws Exception {
                hashMapRegions = viewModel.getRegion(psiLevelsModel.getRegionMetaData());
                hashMapRegionsReading = viewModel.getRegionsReading(psiLevelsModel.getItems().get(0).getReadings());

                setData();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    /**
     * set data to map as marker and set zoom level
     */

    private void setData() {
        LatLng latLngWest = hashMapRegions.get("west");
        LatLng latLngEast = hashMapRegions.get("east");
        LatLng latLngNorth = hashMapRegions.get("north");
        LatLng latLngSouth = hashMapRegions.get("south");
        LatLng latLngCentral = hashMapRegions.get("central");

        mMap.addMarker(new MarkerOptions().position(latLngWest).title("West")).setTag("West");
        mMap.addMarker(new MarkerOptions().position(latLngEast).title("East")).setTag("East");
        mMap.addMarker(new MarkerOptions().position(latLngNorth).title("North")).setTag("North");
        mMap.addMarker(new MarkerOptions().position(latLngSouth).title("South")).setTag("South");
        mMap.addMarker(new MarkerOptions().position(latLngCentral).title("Central")).setTag("Central");

        LatLngBounds.Builder ltlngBld = new LatLngBounds.Builder();

        ltlngBld.include(latLngEast);
        ltlngBld.include(latLngWest);
        ltlngBld.include(latLngNorth);
        ltlngBld.include(latLngSouth);
        ltlngBld.include(latLngCentral);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(ltlngBld.build(), 100));
        mMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        String tag = (String) marker.getTag();
        Log.d(TAG, "onMarkerClick::" + tag);
        // set to true as it will be shown
        isPSIReadingShowing = true;
        toggleList();
        // It will get PSI level reading for selected marker
        ArrayList<String> regionReading = getPSILevelReading(tag);

        // Updated adapter of recycler view
        adapter.updateReading(regionReading);
        // Updated recycler view with data
        adapter.notifyDataSetChanged();
        return false;
    }

    /**
     * It will hide the list of reading for the opened region
     *
     * @param latLng
     */
    @Override
    public void onMapClick(LatLng latLng) {
        isPSIReadingShowing = false;
        toggleList();

    }

    /**
     * It will show / hide list
     */
    private void toggleList() {
        if (!isPSIReadingShowing) {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.original_to_bottom);
            rclrvwPSILevels.setAnimation(anim);
            rclrvwPSILevels.setVisibility(View.GONE);
        } else {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_to_original);
            rclrvwPSILevels.setAnimation(anim);
            rclrvwPSILevels.setVisibility(View.VISIBLE);
        }
    }

    /**
     * It will manage the list if it is opened
     */
    @Override
    public void onBackPressed() {
        if (isPSIReadingShowing) {
            isPSIReadingShowing = false;
            toggleList();
        } else {
            super.onBackPressed();
        }

    }
}
