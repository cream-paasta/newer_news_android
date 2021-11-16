package com.example.newernews.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.newernews.R
import com.example.newernews.data.location.LocationModule
import com.example.newernews.domain.model.KoreanAddress
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val locationModule: LocationModule
) {
    fun getLocationAddress(): Single<KoreanAddress> {
        return Single.create { emitter ->
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(context, R.string.request_permission_location, Toast.LENGTH_SHORT).show()
                emitter.onError(Throwable("doesn't have location permission"))
            } else {
                locationModule.locationClient().lastLocation.addOnSuccessListener {
                    val addressList = locationModule.geocoder().getFromLocation(it.latitude, it.longitude, 1)[0]
                        .getAddressLine(0).split(" ")
                    emitter.onSuccess(KoreanAddress(addressList[1], addressList[2], addressList[3]))
                }
            }
        }
    }
}