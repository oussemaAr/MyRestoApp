package tn.org.myresto.model

import java.io.Serializable


data class Restaurant(
    val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val category: String,
    val icon: String,
) : Serializable