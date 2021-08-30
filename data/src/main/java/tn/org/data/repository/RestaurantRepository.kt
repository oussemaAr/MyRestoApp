package tn.org.data.repository

import kotlinx.coroutines.flow.Flow
import tn.org.data.local.entity.RestaurantEntity

interface RestaurantRepository {

    suspend fun loadRemoteRestaurants(lat: Double, lng: Double, radius: Int = 1)

    fun loadRestaurants(): Flow<List<RestaurantEntity>>

    suspend fun findRestaurantByID(id: String): RestaurantEntity?

}