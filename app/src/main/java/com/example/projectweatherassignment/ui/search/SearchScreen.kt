package com.example.projectweatherassignment.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projectweatherassignment.HomeScreenNav
import com.example.projectweatherassignment.ui.common.SearchBar
import com.example.projectweatherassignment.ui.theme.ProjectWeatherAssignmentTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    ProjectWeatherAssignmentTheme {

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeContent), // Ensure content respects system bars,
            topBar = {
                SearchBar(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .padding(
                            top = 44.dp,
                            start = 24.dp,
                            end = 24.dp
                        ),
                    query = viewModel.state.searchQuery,
                    onQueryChange = {
                        viewModel.onEvent(SearchEvent.OnSearchQueryChange(it))
                    }
                )
            },
            content = { innerPadding ->

                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {

                    SearchResultList(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                        currentWeather = viewModel.state.currentWeatherList
                    ) { currentWeather ->
                        focusManager.clearFocus()
                        viewModel.onEvent(SearchEvent.OnSaveSelectedCity(currentWeather))
                        navController.navigate(HomeScreenNav) {
                            popUpTo(HomeScreenNav) {
                                inclusive = true
                            }
                        }
                    }

                    //show loader
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                }

            }
        )

        // Log inside LaunchedEffect
        LaunchedEffect(viewModel.state.countries) {
            println("log list compose ${viewModel.state.countries.map { it.name }}")

            if (viewModel.state.countries.isNotEmpty())
                viewModel.onEvent(SearchEvent.OnCountryListResult(viewModel.state.countries.map { it.name }))
        }

        // Automatically request focus when the composable is displayed
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }


    }

}

