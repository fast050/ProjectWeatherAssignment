package com.example.projectweatherassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common.model.CurrentWeather
import com.example.projectweatherassignment.ui.home.HomeScreen
import com.example.projectweatherassignment.ui.search.SearchScreen
import com.example.projectweatherassignment.ui.theme.ProjectWeatherAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWeatherAssignmentTheme {
                WeatherApp()
            }
        }
    }
}



@Serializable
object HomeScreenNav

@Serializable
object SearchScreenNav


@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreenNav
    ){

        composable<HomeScreenNav>{
            HomeScreen(
                navController = navController
            )
        }

        composable<SearchScreenNav>{
            SearchScreen(
                navController = navController
            )
        }

    }
}