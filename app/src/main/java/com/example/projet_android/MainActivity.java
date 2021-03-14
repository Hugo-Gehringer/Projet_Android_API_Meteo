package com.example.projet_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;
    public static final int REQUEST_CODE_SECOND_ACTIVITY = 1;


    private double longitude;
    private double latitude;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE_LOCATION);
            return;
        } else {
            processLocation();
        }
        Log.d(TAG, "language : " + Locale.getDefault().getDisplayLanguage());

        EditText editTextCityName = (EditText) findViewById(R.id.inputCitySearch);
        Button buttonCity = (Button) findViewById(R.id.ButtonCitySearch);
        Button buttonLocation = (Button) findViewById(R.id.ButtonLocationSearch);
        Button buttonMap = (Button) findViewById(R.id.ButtonMap);

        editTextCityName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    buttonCity.performClick();
                    return true;
                }
                return false;
            }
        });

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).
                build();
        ImageLoader.getInstance().init(configuration);

        buttonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCityName.getText().toString();
                if (cityName.isEmpty()) {
                    Log.d(TAG, "Enter a Name");
                    Toast toast = Toast.makeText(MainActivity.this, R.string.toast_cityName, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, WeatherPerCityActivity.class);
                    Bundle bundle = new Bundle(1);
                    bundle.putString(WeatherPerCityActivity.INPUT_PARAMETER, cityName);
                    intent.putExtras(bundle);
                    MainActivity.this.startActivity(intent);
                }

            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, WeatherPerLocationActivity.class);
                Bundle bundle = new Bundle(1);
                bundle.putDouble(WeatherPerLocationActivity.INPUT_Longitude, longitude);
                bundle.putDouble(WeatherPerLocationActivity.INPUT_Latitude, latitude);
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                Bundle bundle = new Bundle(1);
                //bundle.putString(WeatherPerLocationActivity.INPUT_Longitude, String.valueOf(longitude));
                //bundle.putString(WeatherPerLocationActivity.INPUT_Latitude, String.valueOf(latitude));
                intent.putExtras(bundle);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public void processLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    protected void tryDisplaylocation(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_LOCATION == requestCode) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                processLocation();
            } else {

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        tryDisplaylocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
