package tn.org.data.mapper

import tn.org.data.local.entity.CategoryEntity
import tn.org.data.local.entity.LocationEntity
import tn.org.data.local.entity.RestaurantEntity
import tn.org.data.network.model.RestaurantResponse

fun List<RestaurantResponse.Response.Venue>.toRestaurantEntity(): List<RestaurantEntity> {
    val list = mutableListOf<RestaurantEntity>()
    this.map { venue ->
        val entity = RestaurantEntity(
            id = venue.id,
            name = venue.name,


            
            location = LocationEntity(
                address = venue.location.formattedAddress.joinToString(" "),
                lat = venue.location.lat,
                lng = venue.location.lng
            ),
            category = if (!venue.categories.isNullOrEmpty()) venue.categories[0].simplify() else null
        )
        list.add(entity)
    }
    return list
}

private fun RestaurantResponse.Response.Venue.Category.simplify(): CategoryEntity {
    return CategoryEntity(
        name = this.name,
        icon = "${this.icon.prefix}64${this.icon.suffix}"
    )
}
