package tn.org.myresto.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import timber.log.Timber
import tn.org.myresto.R
import tn.org.myresto.databinding.MapScreenBinding
import tn.org.myresto.ui.main.MainViewModel
import tn.org.myresto.ui.main.MainViewModelFactory
import tn.org.myresto.utils.distance
import tn.org.myresto.utils.format
import tn.org.myresto.utils.locationFlow
import tn.org.myresto.utils.observeMap


@FlowPreview
@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class RestaurantMapFragment : Fragment() {

    private lateinit var binding: MapScreenBinding
    private var googleMap: GoogleMap? = null
    private lateinit var position: LatLng

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapScreenBinding.inflate(
            inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val map = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment

        initMap(map)

        lifecycleScope.launchWhenCreated {
            locationFlow(requireContext()).collect {
                position = LatLng(it.latitude, it.longitude)
                googleMap?.addMarker {
                    position(LatLng(it.latitude, it.longitude))
                }
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16F))
                viewModel.loadRemoteRestaurants(
                    position.latitude.format(3),
                    position.longitude.format(3),
                    googleMap?.distance()!!.toInt()
                )
            }

            viewModel.data.collect { data ->
                data.forEach { restaurant ->
                    googleMap?.addMarker {
                        position(LatLng(restaurant.lat, restaurant.lng))
                        title(restaurant.name)
                    }?.tag = restaurant.id
                }
            }
        }
    }

    @SuppressLint("PotentialBehaviorOverride")
    fun initMap(map: SupportMapFragment?) {
        lifecycleScope.launchWhenStarted {
            googleMap = map?.awaitMap()

            googleMap?.setOnMarkerClickListener {
                val destination =
                    RestaurantMapFragmentDirections.restaurantDetail(
                        it.tag?.toString() ?: ""
                    )
                findNavController().navigate(destination)
                true
            }

            googleMap?.observeMap()!!
                .debounce(2000)
                .collectLatest {
                    Timber.e("distance $it")
                    viewModel.loadRemoteRestaurants(
                        position.latitude,
                        position.longitude,
                        it.toInt()
                    )
                }
        }
    }
}