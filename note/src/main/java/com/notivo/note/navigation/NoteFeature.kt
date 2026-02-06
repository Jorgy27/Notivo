package com.notivo.note.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notivo.common.navigation.Destination
import com.notivo.common.navigation.Feature
import com.notivo.common.navigation.SubGraphDestination

interface NoteFeature : Feature

class NoteFeatureImpl : NoteFeature {
    override fun registerNavigationGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.Note>(startDestination = Destination.Note) {
            composable<Destination.Note> {
                /*NoteScreen{
                    //navHostController.navigate()
                }*/
            }
        }
    }
}