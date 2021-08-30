package tn.org.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import tn.org.data.local.RestaurantDatabase
import tn.org.data.local.entity.RestaurantEntity
import tn.org.data.mapper.toRestaurantEntity
import tn.org.data.network.service.RestaurantService

class RestaurantRepositoryImpl(
    private val restaurantService: RestaurantService,
    private val restaurantDatabase: RestaurantDatabase
) : RestaurantRepository {

    override suspend fun loadRemoteRestaurants(lat: Double, lng: Double, radius: Int) {
        val request = restaurantService.getSurroundingRestaurants("$lat, $lng", radius)

        if (request.isSuccessful) {
            val body = request.body()!!
            val restaurants = body.response.venues.toRestaurantEntity()
            restaurantDatabase.restaurantDao().insertRestaurantsList(restaurants)
        }
    }

    override fun loadRestaurants(): Flow<List<RestaurantEntity>> {
        return restaurantDatabase.restaurantDao().getAllRestaurants()
    }

    override suspend fun findRestaurantByID(id: String): RestaurantEntity {
        Log.e("TAG", "findRestaurantByID: $id")
        Log.e(
            "TAG",
            "findRestaurantByID: ${restaurantDatabase.restaurantDao().getRestaurantByID(id)}",
        )
        return restaurantDatabase.restaurantDao().getRestaurantByID(id)

    }

}

