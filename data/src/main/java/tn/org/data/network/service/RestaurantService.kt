package tn.org.data.network.service

import androidx.annotation.IntRange
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tn.org.data.utils.SEARCH_ENDPOINT
import tn.org.data.network.model.RestaurantResponse

interface RestaurantService {

    @GET(SEARCH_ENDPOINT)
    suspend fun getSurroundingRestaurants(
        @Query("ll") latLng: String,
        @Query("radius") @IntRange(from = 0L, to = 100000L) radius: Int
    ): Response<RestaurantResponse>


}