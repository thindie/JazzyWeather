package com.example.thindie.core.localrawresources.ldo

import com.google.gson.annotations.SerializedName


data class LocationPropertiesLdo(
    @SerializedName("admin_name")
    val adminName: String,
    val capital: String,
    val city: String,
    val country: String,
    val iso2: String,
    val lat: String,
    val lng: String,
    val population: String,
    @SerializedName("population_proper")
    val populationProper: String,
)