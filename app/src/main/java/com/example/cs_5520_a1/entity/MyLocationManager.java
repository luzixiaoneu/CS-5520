package com.example.cs_5520_a1.entity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

public class MyLocationManager implements LocationListener {
    Context context;
    private double latitude;
    private double longitude;
    private double distance;

    public MyLocationManager(Context context) {
        this.context = context;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        Log.d("info", latitude + ":" + longitude);
        Toast.makeText(context, latitude + ":" + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
