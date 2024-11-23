package com.sopkathon.team2.presentation.main

import MainNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sopkathon.team2.ui.theme.ANDSOPTSOPKATHONTEAM2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDSOPTSOPKATHONTEAM2Theme {
                val navController = rememberNavController()
                val navigator = MainNavigator(navController)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavHost(
                        navigator = navigator,
                        padding = innerPadding
                    )
                }
            }
        }
    }
}