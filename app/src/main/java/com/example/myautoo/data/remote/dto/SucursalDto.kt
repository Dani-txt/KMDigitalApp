package com.example.myautoo.data.remote.dto

data class SucursalDto(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val comuna: ComunaDto?
)
