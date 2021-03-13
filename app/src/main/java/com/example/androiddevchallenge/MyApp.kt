package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.math.log

const val landing = "landing"
const val login = "login"
const val home = "home"

@Composable
fun MyApp(startDestination: String = login) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(landing) {
            Landing(navController = navController)
        }
        composable(login) {
            Login(navController = navController)
        }
        composable(home) {
            Home(navController = navController)
        }
    }
}