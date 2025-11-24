package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.data.remote.AppDatabase
import com.example.weatherapp.navigation.BottomBar
import com.example.weatherapp.navigation.BottomNavItem
import com.example.weatherapp.navigation.Routes
import com.example.weatherapp.ui.screens.DetailScreen
import com.example.weatherapp.ui.screens.HomeScreen
import com.example.weatherapp.ui.screens.HomeScreen2
import com.example.weatherapp.ui.screens.SavedDaoScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.viewmodel.ItemViewModel
import com.example.weatherapp.ui.viewmodel.ItemViewModelFactory
import com.example.weatherapp.ui.viewmodel.MainViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(application).itemDao()
        val factory = ItemViewModelFactory(dao)

        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(application)
                }
            }
        }
    }
}

@Composable
fun App(application: Context) {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home,  BottomNavItem.Add, BottomNavItem.Others)

    val weatherViewModel: WeatherViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomBar(navController, bottomItems) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {

            composable(Routes.OTHERS) {
                val vm: MainViewModel = viewModel()
                HomeScreen(viewModel = vm, onItemClick = { id ->
                    navController.navigate(Routes.detailRoute(id))
                })
            }


            composable(Routes.HOME) {
                HomeScreen2(weatherViewModel)
            }


            composable(Routes.ADD) {
                val dao = AppDatabase.getDatabase(application).itemDao()
                val factory = ItemViewModelFactory(dao)
                val viewModel: ItemViewModel = viewModel(factory = factory)
                SavedDaoScreen(viewModel) }
            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val vm: MainViewModel = viewModel()
                val id = backStackEntry.arguments?.getInt("itemId") ?: -1
                DetailScreen(itemId = id, viewModel = vm, onBack = { navController.popBackStack() })
            }
        }
    }
}