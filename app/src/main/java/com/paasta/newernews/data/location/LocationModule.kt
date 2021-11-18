package com.paasta.newernews.data.location

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class LocationModule @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun locationClient(): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    fun geocoder(): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }
}