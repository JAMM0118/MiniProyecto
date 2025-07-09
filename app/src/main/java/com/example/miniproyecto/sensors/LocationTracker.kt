package com.example.miniproyecto.sensors

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*

class LocationTracker(private val context: Context) {
    private val fusedClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.create()
        .setInterval(5000)
        .setFastestInterval(3000)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY)

    private var lastLocation: Location? = null
    private val _distanceInMeters = MutableLiveData<Float>(0f)
    val distanceInMeters: LiveData<Float> = _distanceInMeters

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) return

        fusedClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val newLocation = result.lastLocation ?: return
                lastLocation?.let {
                    val increment = it.distanceTo(newLocation)
                    _distanceInMeters.value = (_distanceInMeters.value ?: 0f) + increment
                }
                lastLocation = newLocation
            }
        }, null)
    }
}
