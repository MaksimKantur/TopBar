package com.example.topbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost;
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.topbar.ui.theme.TopBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false)}
    TopAppBar(
        title = { Text(title)},
        actions ={
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = !expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {navController.navigate("info")}) {
                    Text("Info")
                }
                DropdownMenuItem(
                    onClick = {navController.navigate("settings")}) {
                    Text("Settings")
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = { MainTopBar("My app", navController)},
        content = {Text(text= "Content for Home screen")},
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold (
        topBar = { MainTopBar("My app", navController)},
        content = {Text(text= "Content for Info screen")},
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold (
        topBar = { MainTopBar("My app", navController)},
        content = {Text(text= "Content for Settings screen")},
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            MainScreen(navController)
        }
        composable("Info") {
            InfoScreen(navController)
        }
        composable("Settings") {
            SettingsScreen(navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TopBarTheme {
        Greeting("Android")
    }
}