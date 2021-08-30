package tn.org.myresto.utils

import tn.org.data.local.entity.RestaurantEntity
import tn.org.myresto.model.Restaurant


fun RestaurantEntity.asRestaurant(): Restaurant {
    return Restaurant(
        id = this.id,
        name = this.name,
        address = this.location.address,
        lat = this.location.lat,
        lng = this.location.lng,
        category = this.category?.name ?: "",
        icon = this.category?.icon ?: ""
    )
}