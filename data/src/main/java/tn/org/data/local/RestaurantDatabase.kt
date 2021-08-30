package tn.org.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.org.data.local.dao.RestaurantDao
import tn.org.data.local.entity.RestaurantEntity


@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao


    companion object {

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getInstance(context: Context): RestaurantDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                ).build()
            }
        }
    }

}