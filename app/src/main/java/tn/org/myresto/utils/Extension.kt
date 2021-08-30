package tn.org.myresto.utils

import android.location.Location
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.runBlocking


@ExperimentalCoroutinesApi
fun GoogleMap.observeMap(): Flow<Float> = channelFlow {
    setOnCameraMoveListener {
        runBlocking {
            trySend(distance())
        }
    }
    awaitClose()
}

fun GoogleMap.distance(): Float {
    val bounds = projection.visibleRegion.latLngBounds
    val array = FloatArray(1)
    Location.distanceBetween(
        bounds.center.latitude,
        bounds.center.longitude,
        bounds.northeast.latitude,
        bounds.northeast.longitude,
        array
    )
    return array[0]
}


fun Double.format(digits: Int) = "%.${digits}f".format(this).toDouble()

@BindingAdapter("image_url")
fun loadUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}