package tn.org.myresto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import tn.org.data.repository.RestaurantRepository
import tn.org.myresto.model.Restaurant
import tn.org.myresto.utils.asRestaurant

class MainViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {


    private val _detail = MutableLiveData<Restaurant>()
    val detail: LiveData<Restaurant> = _detail

    fun loadDetails(id: String) = viewModelScope.launch {
        Timber.e(id)
        if (repository.findRestaurantByID(id) != null) {
            _detail.postValue(repository.findRestaurantByID(id)?.asRestaurant())
        } else {
            Timber.e("EMPTY")
        }

    }

    fun loadRemoteRestaurants(lat: Double, lng: Double, radius: Int) = viewModelScope.launch {
        repository.loadRemoteRestaurants(
            lat,
            lng,
            radius
        )
    }

    @FlowPreview
    var data = repository.loadRestaurants().map {
        it.map { entity ->
            entity.asRestaurant()
        }
    }
        .flowOn(Dispatchers.Main)
        .debounce(1000)
        .catch {
            emit(emptyList())
        }

}