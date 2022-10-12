package com.example.baemin.data.repository.map

import android.util.Log
import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.network.MapApiService
import com.example.baemin.data.response.address.AddressInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultMapRepository(
    private val mapApiService: MapApiService,
    private val ioDispatcher: CoroutineDispatcher
): MapRepository {
    override suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo? = withContext(ioDispatcher) {
        val response = mapApiService.getReverseGeoCode(
            lat = locationLatLngEntity.latitude,
            lon = locationLatLngEntity.longitude
//            lat = 37.4980,
//            lon = 127.0276
        )
        if (response.isSuccessful) {
            response?.body()?.addressInfo
        } else {
            null
        }
    }
}