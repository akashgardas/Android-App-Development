package com.example.ordercupcakes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class CupcakeScreen {
    Start, Flavor, Pickup, Summary
}

@Composable
fun CupcakeNavHost(
    navController: NavHostController,
    viewModel: OrderViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = CupcakeScreen.Start.name,
    ) {
        composable(route = CupcakeScreen.Start.name) {
            StartScreen(
                onNextButtonClicked = {
                    viewModel.setQuantity(it)
                    navController.navigate(CupcakeScreen.Flavor.name)
                }
            )
        }

        composable(route = CupcakeScreen.Flavor.name) {
            FlavorScreen(
                onNext = { navController.navigate(CupcakeScreen.Pickup.name) },
                onCancel = {
                    viewModel.resetOrder()
                    navController.popBackStack(
                        CupcakeScreen.Start.name,
                        inclusive = false
                    )
                },
                viewModel = viewModel
            )
        }
        composable(route = CupcakeScreen.Pickup.name) {
            PickUpScreen(
                onNext = {
                    navController.navigate(CupcakeScreen.Summary.name)
                },
                onCancel = {
                    viewModel.resetOrder()
                    navController.popBackStack(
                        CupcakeScreen.Start.name,
                        inclusive = false
                    )
                },
                viewModel = viewModel
            )
        }

        composable(CupcakeScreen.Summary.name) {
            SummaryScreen(
                viewModel=viewModel,
                onCancelButtonClicked = {
                    viewModel.resetOrder()
                    navController.popBackStack(
                        CupcakeScreen.Start.name,
                        inclusive = false
                    )
                }
            )
        }
    }
}
