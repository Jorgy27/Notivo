package com.notivo.home.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notivo.common.R
import com.notivo.common.navigation.Destination
import com.notivo.common.navigation.Feature
import com.notivo.common.navigation.SubGraphDestination
import com.notivo.common.view.composables.dialogs.OptionsDialog
import com.notivo.common.view.configs.DialogConfig
import com.notivo.home.composables.HomeScreen

interface HomeFeature : Feature {}

class HomeFeatureImpl : HomeFeature {
    override fun registerNavigationGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<SubGraphDestination.Home>(startDestination = Destination.Home) {
            composable<Destination.Home> {
                val options = listOf(
                    "Add sub-note",
                    "Bookmark",
                    "Private",
                    "Folder",
                    "Share",
                )

                val icons = listOf(
                    R.drawable.note_stack_add,
                    R.drawable.bookmark,
                    R.drawable.lock,
                    R.drawable.folder,
                    R.drawable.share,
                )
                val showDialog = remember { mutableStateOf(false) }
                HomeScreen {
                    //navHostController.navigate(Destination.MyLists)
                    showDialog.value = true
                }

                if (showDialog.value) {
                    val context = LocalContext.current
                    OptionsDialog(
                        modifier = Modifier
                            .width(180.dp),
                        options = options,
                        trailingIconList = icons,
                        dialogItemInfo = DialogConfig.AppDialogItemInfo(
                            isCancelable = true,
                            onNegativeButtonClicked = { showDialog.value = false }
                        )
                    ) {
                        Toast.makeText(
                            context,
                            "Clicked on ${options[it]}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}