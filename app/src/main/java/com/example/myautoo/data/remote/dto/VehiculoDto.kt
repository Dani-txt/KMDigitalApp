package com.example.myautoo.data.remote.dto

data class VehiculoDto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val anio: Int,
    val kilometraje: Int,
    val estadoVenta: String,
    val imagenVehiculo: String,

    val carroceria: CarroceriaDto?,
    val modelo: ModeloDto?,
    val categoria: CategoriaDto?,
    val paisOrigen: PaisOrigenDto?,
    val tipoCombustible: TipoCombustibleDto?,
    val sucursal: SucursalDto?,
    val transmision: TransmisionDto?
)
