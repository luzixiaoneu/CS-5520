package com.example.cs_5520_a1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs_5520_a1.entity.MyLocationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GpsActivity extends AppCompatActivity {
    public double latitude;
    public double longitude;
    public float distance;
    public Location lastLocation;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Button resetButton = findViewById(R.id.reset_distance);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distance = 0;
                TextView distance_info = findViewById(R.id.distance);
                distance_info.setText("Distance: " + distance + " m");
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(GpsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1 );
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }

        else {
            getLocation();
        }


    }

    public void getLocation() {
        LocationListener myLocationListener = new LocationListener() {




            @Override
            public void onLocationChanged(@NonNull Location location) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                if (lastLocation == null) {
                    distance = 0.0f;
                }
                else {
                    float tmpDistance = location.distanceTo(lastLocation);
                    distance += tmpDistance;
                }

                lastLocation = new Location("");
                lastLocation.setLatitude(latitude);
                lastLocation.setLongitude(longitude);
                TextView info = findViewById(R.id.lat_lon);
                TextView distance_info = findViewById(R.id.distance);
                distance_info.setText("Distance: " + String.format("%.02f", distance) + " m");
                info.setText("Current Latitude: " + latitude + "\nCurrent longitude: " + longitude);
                Log.d("info", latitude + ":" + longitude);
//               Toast.makeText(getApplicationContext(), latitude + "-" + longitude, Toast.LENGTH_SHORT).show();
            }
        };
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30, 10, myLocationListener);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (!Arrays.asList(grantResults).contains(PackageManager.PERMISSION_DENIED)) {
                //all permissions have been granted
                getLocation();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putDouble("lat", this.latitude);
        outState.putDouble("long", this.longitude);
        outState.putFloat("distance", this.distance);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        this.latitude = savedInstanceState.getDouble("lat");
        this.longitude = savedInstanceState.getDouble("long");
        this.distance = savedInstanceState.getFloat("distance");
        this.lastLocation = new Location("");
        this.lastLocation.setLatitude(this.latitude);
        this.lastLocation.setLongitude(this.longitude);
        TextView distance_info = findViewById(R.id.distance);
        distance_info.setText("Distance: " + String.format("%.02f", distance) + " m");
        TextView info = findViewById(R.id.lat_lon);
        info.setText("Current Latitude: " + latitude + "\nCurrent longitude: " + longitude);
        super.onRestoreInstanceState(savedInstanceState);
    }

}