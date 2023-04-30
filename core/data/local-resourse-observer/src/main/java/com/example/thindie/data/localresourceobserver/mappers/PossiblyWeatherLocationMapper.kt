package com.example.thindie.data.localresourceobserver.mappers

import com.example.thindie.core.localrawresources.ldo.LocationPropertiesLdo
import com.example.thindie.domain.localresourceobserver.entity.PossiblyWeatherLocation

fun LocationPropertiesLdo.toPossiblyWeatherLocation() = PossiblyWeatherLocation(
    adminName = this.admin_name,
    capital = this.capital,
    city = this.city,
    country = this.country,
    iso2 = this.iso2,
    latitude = this.lat,
    longitude = this.lng,
    population = this.population,
    populationProper = this.population_proper
)
