package tn.org.myresto.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
fun locationFlow(
    context: Context
): Flow<Location> = flow {
    val locationClient = LocationServices.getFusedLocationProviderClient(context)
    locationClient.lastLocation.await<Location?>()?.let {
        emit(it)
    }
}
