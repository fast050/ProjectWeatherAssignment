package com.example.projectweatherassignment.ui.search

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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projectweatherassignment.HomeScreenNav
import com.example.projectweatherassignment.ui.common.SearchBar
import com.example.projectweatherassignment.ui.theme.GrayF2
import com.example.projectweatherassignment.ui.theme.ProjectWeatherAssignmentTheme
import com.example.projectweatherassignment.ui.theme.Red30

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    ProjectWeatherAssignmentTheme {

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val snackBarHostState = remember { SnackbarHostState() }

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

            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) {
                    Snackbar(
                        containerColor = Red30,
                        contentColor = GrayF2,
                    ){
                        Text(
                            text = viewModel.state.errorMessage ?: "Ops! Something went wrong",
                            fontSize = 12.sp ,
                            fontWeight = Bold
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

        LaunchedEffect(viewModel.state.errorMessage) {
            viewModel.state.errorMessage?.let{ errorMessage ->
                snackBarHostState.showSnackbar(message = errorMessage)
            }
        }


    }

}

