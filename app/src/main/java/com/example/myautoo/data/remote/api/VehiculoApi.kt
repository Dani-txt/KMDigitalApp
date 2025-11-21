package com.example.myautoo.data.remote.api

import com.example.myautoo.data.remote.dto.VehiculoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VehiculoApi {

    @GET("api/v1/vehiculos")
    suspend fun getVehiculos(): List<VehiculoDto>

    @GET("api/v1/vehiculos/{id}")
    suspend fun getVehiculoById(@Path("id") id: Int): VehiculoDto
}
