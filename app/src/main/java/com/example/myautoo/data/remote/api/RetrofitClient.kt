package com.example.myautoo.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myautoo.data.remote.api.MarcaApi
import com.example.myautoo.data.remote.api.VehiculoApi


object RetrofitClient {

    private const val BASE_URL = "https://kmdigital-backend-3ebf.onrender.com/"

    val marcaApi: MarcaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarcaApi::class.java)
    }

    val vehiculoApi: VehiculoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VehiculoApi::class.java)
    }
}

