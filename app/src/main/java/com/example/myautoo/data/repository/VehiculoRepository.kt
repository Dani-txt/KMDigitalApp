package com.example.myautoo.data.repository

import com.example.myautoo.data.remote.api.RetrofitClient
import com.example.myautoo.data.remote.dto.VehiculoDto

class VehiculoRepository {

    private val api = RetrofitClient.vehiculoApi

    suspend fun getVehiculos(): List<VehiculoDto> {
        return api.getVehiculos()
    }

    suspend fun getVehiculoById(id: Int): VehiculoDto {
        return api.getVehiculoById(id)
    }
}
