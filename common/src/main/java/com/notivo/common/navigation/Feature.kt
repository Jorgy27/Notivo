package com.notivo.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface Feature {

    fun registerNavigationGraph(navHostController: NavHostController, navGraphBuilder: NavGraphBuilder)
}