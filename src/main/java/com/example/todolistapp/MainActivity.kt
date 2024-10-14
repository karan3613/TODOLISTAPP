package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.Screens.EditScreen
import com.example.todolistapp.Screens.InsertScreen
import com.example.todolistapp.Screens.MainScreen
import com.example.todolistapp.ui.theme.TODOLISTAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOLISTAPPTheme {

                val navController = rememberNavController()
                NavHost(navController , startDestination = "MainApp"){
                    navigation(startDestination = "MainScreen", route = "MainApp"){
                        composable(route = "MainScreen") {
                            MainScreen(navController= navController)
                        }
                        composable(route = "EditScreen/{id}"){
                            val taskId = it.arguments?.getString("id")?.toIntOrNull() ?: 0
                            EditScreen(navController , taskId = taskId)
                        }
                        composable(route = "InsertScreen"){
                            InsertScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}

