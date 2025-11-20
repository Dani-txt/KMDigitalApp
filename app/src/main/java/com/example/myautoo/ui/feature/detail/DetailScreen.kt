package com.example.myautoo.ui.feature.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myautoo.data.remote.api.VehiculoApi
import com.example.myautoo.data.remote.dto.VehiculoDto
import com.example.myautoo.ui.viewModel.CartViewModel

@Composable
fun DetailScreen(
    car: VehiculoDto,
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit,
    cartViewModel: CartViewModel
) {
    val scroll = rememberScrollState()
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DetailHeader(car.imagenVehiculo, onBack, onBack)

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 450.dp)
                .verticalScroll(scroll)
        ) {
            DetailInfo(
                title = car.nombre,
                subtitle = car.modelo?.nombre ?: "Modelo desconocido",
                description = car.descripcion
            )

            DetailPriceBar(
                price = car.precio,
                onAddToCart = {
                    cartViewModel.addToCart(car)
                    Toast.makeText(context, "${car.nombre} añadido al carrito", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}
