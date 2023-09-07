package com.example.thindie.local_resorces_location_fetcher.mapper

import com.example.thindie.core.localrawresources.ldo.LocationPropertiesLdo
import com.example.thindie.domain.entities.WeatherLocation

fun LocationPropertiesLdo.map(): WeatherLocation {
    return WeatherLocation(
        adminName = adminName,
        capital = capital,
        city = city,
        country = country,
        iso2 = iso2,
        latitude = latitude,
        longitude = longitude,
        population = population,
        populationProper = populationProper
    )
}