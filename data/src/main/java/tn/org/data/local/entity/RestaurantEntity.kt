package tn.org.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_table")
data class RestaurantEntity(

    @PrimaryKey @ColumnInfo(name = "restaurant_id") val id: String,
    @ColumnInfo(name = "restaurant_name") val name: String,
    @Embedded val location: LocationEntity,
    @Embedded val category: CategoryEntity?

)