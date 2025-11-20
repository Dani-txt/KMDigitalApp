package com.example.myautoo.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myautoo.data.remote.dto.VehiculoDto
import com.example.myautoo.data.repository.VehiculoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CarViewModel : ViewModel() {

    private val repo = VehiculoRepository()

    private val _cars = MutableStateFlow<List<VehiculoDto>>(emptyList())
    val cars: StateFlow<List<VehiculoDto>> = _cars.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val filteredCars = combine(_cars, _searchText) { cars, text ->
        if (text.isBlank()) cars
        else cars.filter { it.nombre.contains(text, ignoreCase = true) }
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        loadCars()
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun loadCars() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _cars.value = repo.getVehiculos()
            } catch (e: Exception) {
                _error.value = "Error loading cars: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
