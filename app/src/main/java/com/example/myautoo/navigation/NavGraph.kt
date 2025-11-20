package com.example.myautoo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.myautoo.data.repository.CartRepository
import com.example.myautoo.data.local.database.CarDatabase
import com.example.myautoo.data.remote.dto.VehiculoDto
import com.example.myautoo.ui.feature.auth.LoginScreen
import com.example.myautoo.ui.feature.auth.ProfileScreen
import com.example.myautoo.ui.feature.auth.RegisterScreen
import com.example.myautoo.ui.feature.cart.CartScreen
import com.example.myautoo.ui.feature.detail.DetailScreen
import com.example.myautoo.ui.feature.home.MainScreen
import com.example.myautoo.ui.viewModel.*

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    // ROOM database
    val database = Room.databaseBuilder(
        context,
        CarDatabase::class.java,
        "car_database"
    ).build()

    val cartRepository = CartRepository(database.cartDao())

    val cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory(cartRepository))
    val categoryViewModel: CategoryViewModel = viewModel()
    val carViewModel: CarViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screens.HOME) {

        composable(Screens.LOGIN) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }

        composable(Screens.REGISTER) {
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }

        composable(Screens.HOME) {
            MainScreen(
                onProfileClick = { navController.navigate(Screens.PROFILE) },
                onCarClick = { car ->
                    navController.navigate("detail/${car.id}")
                },
                onCartClick = { navController.navigate(Screens.CART) },
                carViewModel = carViewModel,
                categoryViewModel = categoryViewModel
            )
        }

        composable(Screens.PROFILE) {
            ProfileScreen(navController = navController, authViewModel = authViewModel)
        }

        //DETAIL recibe el id del auto
        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull()

            // Obtener el listado actual
            val cars = carViewModel.cars.collectAsState().value

            // Buscar el auto por ID
            val car = cars.find { it.id == id }

            if (car != null) {
                DetailScreen(
                    car = car,
                    onBack = { navController.popBackStack() },
                    onNavigateToCart = { navController.navigate(Screens.CART) },
                    cartViewModel = cartViewModel
                )
            }
        }

        composable(Screens.CART) {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                authViewModel = authViewModel
            )
        }
    }
}

object Screens {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val CART = "cart"
}
