package com.notivo.mylists.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notivo.common.navigation.Destination
import com.notivo.common.navigation.Feature
import com.notivo.common.navigation.SubGraphDestination
import com.notivo.mylists.composables.MyListsScreen

/**Feature specific interface, created for better semantics*/
interface MyListsFeature : Feature

class MyListsFeatureImpl : MyListsFeature{
    override fun registerNavigationGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.MyLists>(startDestination = Destination.MyLists) {
            composable<Destination.MyLists> {
                MyListsScreen{
                    //navHostController.navigate()
                }
            }
        }
    }

}