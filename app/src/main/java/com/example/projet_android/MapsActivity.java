package com.example.projet_android;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button buttonMarker=(Button) findViewById(R.id.ButtonPerMarker);


        buttonMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, WeatherPerLocationActivity.class);
                Bundle bundle = new Bundle(1);
                bundle.putString(WeatherPerLocationActivity.INPUT_Longitude, String.valueOf(longitude));
                bundle.putString(WeatherPerLocationActivity.INPUT_Latitude, String.valueOf(latitude));
                intent.putExtras(bundle);
                MapsActivity.this.startActivity(intent);
            }
        });


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng mtp = new LatLng(43.6108, 3.8767);
        float zoomLevel = 7; //This goes up to 21
        latitude=mtp.latitude;
        longitude=mtp.longitude;

        mMap.addMarker(new MarkerOptions().position(mtp));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mtp, zoomLevel));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                latitude=latLng.latitude;
                longitude=latLng.longitude;

                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);

            }
        });

    }


}