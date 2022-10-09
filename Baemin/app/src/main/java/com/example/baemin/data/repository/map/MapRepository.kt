package com.example.baemin.data.repository.map

import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.response.address.AddressInfo

interface MapRepository {

    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo?
}