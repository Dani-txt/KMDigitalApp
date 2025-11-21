package com.example.myautoo.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myautoo.data.repository.CartRepository
import com.example.myautoo.data.local.entity.CartItemEntity
import com.example.myautoo.data.remote.dto.VehiculoDto
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val cartItems = repository.cartItems
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val totalPrice: StateFlow<Double> = cartItems
        .map { items -> items.sumOf { it.price } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0.0
        )

    fun addToCart(car: VehiculoDto) {
        viewModelScope.launch {
            repository.addCar(
                CartItemEntity(
                    carId = car.id,
                    title = car.nombre,
                    price = car.precio
                )
            )
        }
    }

    fun removeFromCart(item: CartItemEntity) {
        viewModelScope.launch {
            repository.removeCar(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}
