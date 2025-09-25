package com.notivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.notivo.common.navigation.SubGraphDestination
import com.notivo.ui.theme.NotivoTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var defaultNavigator: DefaultNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotivoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        MainNavigation(defaultNavigator = defaultNavigator)
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier, defaultNavigator: DefaultNavigator){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SubGraphDestination.Home
    ) {
        defaultNavigator.homeFeature.registerNavigationGraph(navController,  this)
        // add more destinations here.
    }
}