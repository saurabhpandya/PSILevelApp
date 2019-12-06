package com.psilevelapp.psilevel;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.psilevelapp.R;
import com.psilevelapp.model.PSILevelsModel;
import com.psilevelapp.psilevel.viewmodel.PSILevelViewModel;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap;

    private PSILevelViewModel viewModel;

    private HashMap<String, LatLng> hashMapRegions;
    private HashMap<String, HashMap<String, Integer>> hashMapRegionsReading;

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

        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         */

        getData();
    }

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

    private void setData() {
        LatLng latLngWest = hashMapRegions.get("west");
        LatLng latLngEast = hashMapRegions.get("east");
        LatLng latLngNorth = hashMapRegions.get("north");
        LatLng latLngSouth = hashMapRegions.get("south");
        LatLng latLngCentral = hashMapRegions.get("central");

        mMap.addMarker(new MarkerOptions().position(latLngWest).title("West"));
        mMap.addMarker(new MarkerOptions().position(latLngEast).title("East"));
        mMap.addMarker(new MarkerOptions().position(latLngNorth).title("North"));
        mMap.addMarker(new MarkerOptions().position(latLngSouth).title("South"));
        mMap.addMarker(new MarkerOptions().position(latLngCentral).title("Central"));

        LatLngBounds.Builder ltlngBld = new LatLngBounds.Builder();

        ltlngBld.include(latLngEast);
        ltlngBld.include(latLngWest);
        ltlngBld.include(latLngNorth);
        ltlngBld.include(latLngSouth);
        ltlngBld.include(latLngCentral);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(ltlngBld.build(), 100));
    }

}
