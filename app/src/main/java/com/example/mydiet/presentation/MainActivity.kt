package com.example.mydiet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydiet.presentation.ui.DIETS_SCREEN_ROUTE
import com.example.mydiet.presentation.ui.DIET_DETAILS_SCREEN_ROUTE
import com.example.mydiet.presentation.ui.diet.DietScreen
import com.example.mydiet.presentation.ui.diet.DietViewModel
import com.example.mydiet.presentation.ui.dietslist.DietsListScreenControl
import com.example.mydiet.presentation.ui.dietslist.DietsListViewModel
import com.example.mydiet.presentation.ui.theme.Background
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val dietsListViewModel by viewModel<DietsListViewModel>()
    val dietViewModel by viewModel<DietViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                containerColor = Background
            ) { paddingValues ->
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = DIETS_SCREEN_ROUTE,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(DIETS_SCREEN_ROUTE) {
                        DietsListScreenControl(
                            viewModel = dietsListViewModel,
                            onListItemClick = {id -> navigateFromDietsListToDietDetails(id, navController)}
                        )
                    }

                    composable(DIET_DETAILS_SCREEN_ROUTE) {
                        DietScreen(
                            viewModel = dietViewModel,
                            onStatusClick = {}
                        )
                    }
                }
            }
        }
    }

    private fun navigateFromDietsListToDietDetails(id: Long, navController: NavHostController) {
        dietViewModel.dietId = id
        navController.navigate(route = DIET_DETAILS_SCREEN_ROUTE)
    }
}

