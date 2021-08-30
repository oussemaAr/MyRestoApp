package tn.org.myresto.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.org.data.local.RestaurantDatabase
import tn.org.data.network.RestaurantClient
import tn.org.data.repository.RestaurantRepository
import tn.org.data.repository.RestaurantRepositoryImpl


class MainViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val repository: RestaurantRepository = RestaurantRepositoryImpl(
                RestaurantClient.restaurantService,
                RestaurantDatabase.getInstance(context)
            )
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct")
    }
}