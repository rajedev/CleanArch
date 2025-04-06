package com.app.cleararch.multiscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.cleararch.ui.theme.CleanArchTheme
import kotlinx.serialization.Serializable

class MultiScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CleanArchTheme {
                Scaffold(
                    topBar = {
                        Header()
                    }) { paddingValues ->
                    Content(paddingValues, navController)
                }
            }
        }
    }
}

@Composable
private fun Content(paddingValues: PaddingValues, navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        val navigateToScreen2 = {
            navController.navigate(Screens.Route2.route)
        }

        val popBackStack = {
            navController.popBackStack()
        }
        NavHost(
            navController = navController,
            startDestination = Screens.Route1,
        ) {
            composable<Screens.Route1> { Screen1(navigateToScreen2) }
            // composable<Screens.Route2> { Screen2(navController) }
            /* composable(route = Screens.Route1.route){
                 Screen1(navController)
             }*/
            composable(route = Screens.Route2.route) {
                Screen2(popBackStack)
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {
    TopAppBar(
        title = { Text("Multi Screen App") },
        actions = {
            Button(onClick = {}) {
                Text("Click 1")
            }
            Button(onClick = {}) {
                Text("Click 2")
            }
        }
    )
}

@Composable
fun Screen1(navigateToScreen2: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navigateToScreen2()
        }) {
            Text("Screen 1")
        }
    }
}

@Composable
fun Screen2(popBackStack: () -> Boolean) {
    Column(verticalArrangement = Arrangement.Center) {
        Button(onClick = {
            popBackStack()
        }) {
            Text("Screen 2")
        }
    }
}

@Serializable
sealed class Screens(val route: String) {
    @Serializable
    data object Route1 : Screens("test")

    // @Serializable
    data object Route2 : Screens("Screen 2")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanArchTheme {
        Greeting2("Android")
    }
}
