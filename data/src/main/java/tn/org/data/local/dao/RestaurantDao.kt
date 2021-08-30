package tn.org.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tn.org.data.local.entity.RestaurantEntity


@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestaurantsList(restaurantEntity: List<RestaurantEntity>)

    @Query("SELECT * FROM RESTAURANT_TABLE")
    fun getAllRestaurants(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurant_table WHERE restaurant_id like :id")
    suspend fun getRestaurantByID(id: String): RestaurantEntity
}