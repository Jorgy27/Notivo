package com.notivo.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
/**All feature modules must implement the Feature contract.
 * This ensures each feature can contribute its own navigation graph to the app.*/
interface Feature {

    fun registerNavigationGraph(navHostController: NavHostController, navGraphBuilder: NavGraphBuilder)
}