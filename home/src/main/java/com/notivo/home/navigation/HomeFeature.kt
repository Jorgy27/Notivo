package com.notivo.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notivo.home.composables.HomeScreen
import com.notivo.common.navigation.Destination
import com.notivo.common.navigation.Feature
import com.notivo.common.navigation.SubGraphDestination

interface HomeFeature : Feature {}

class HomeFeatureImpl : HomeFeature {
    override fun registerNavigationGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.Home>(startDestination = Destination.Home) {
            composable<Destination.Home> {
                HomeScreen{
                    //navHostController.navigate()
                }
            }
        }
    }

}