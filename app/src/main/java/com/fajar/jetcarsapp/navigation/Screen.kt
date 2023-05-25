package com.fajar.jetcarsapp.navigation



sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about_page")
    object Detail : Screen("home/{carsId}") {
        fun createRoute(carsId: String) = "home/$carsId"
    }
}
