package com.notivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.notivo.common.R
import com.notivo.common.data.NavigationItem
import com.notivo.common.data.TextNote
import com.notivo.common.navigation.SubGraphDestination
import com.notivo.common.utils.previewDialogInfo
import com.notivo.common.view.composables.buttons.AppFloatingButton
import com.notivo.common.view.composables.buttons.CircularFloatingButton
import com.notivo.common.view.composables.buttons.FloatingShadowConfig
import com.notivo.common.view.composables.buttons.RadialFloatingButton
import com.notivo.common.view.composables.dialogs.QuickNoteDialog
import com.notivo.common.view.configs.AppFloatingButtonConfig
import com.notivo.common.view.configs.DialogConfig
import com.notivo.common.view.models.NoteContentUi
import com.notivo.ui.theme.NotivoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var defaultNavigator: DefaultNavigator

    private var viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NotivoTheme {
                val navController = rememberNavController()
                val navigationItems = NavigationItem.getNavigationList()
                var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var showQuickNoteDialog by remember { mutableStateOf(false) }
                val context = LocalContext.current
                var selectedDialogOption: AppFloatingButtonConfig.FloatingButtonAction? = null

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .padding(WindowInsets.systemBars.asPaddingValues())
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            navigationItems.forEachIndexed { index, item ->
                                NavigationDrawerItem(
                                    label = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        navController.navigate(item.route)
                                        selectedItemIndex = index
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    }
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MainNavigation(navController = navController, defaultNavigator = defaultNavigator)

                            val appFloatingButtonInfo = AppFloatingButtonConfig.AppFloatingButtonInfo(
                                childrenInfo = listOf(
                                    AppFloatingButtonConfig.AppFloatingButtonChildInfo(
                                        icon = R.drawable.ic_add,
                                        action = AppFloatingButtonConfig.FloatingButtonAction.ADD_NOTE
                                    ),
                                    AppFloatingButtonConfig.AppFloatingButtonChildInfo(
                                        icon = R.drawable.ic_create_folder,
                                        action = AppFloatingButtonConfig.FloatingButtonAction.ADD_FOLDER
                                    ),
                                    AppFloatingButtonConfig.AppFloatingButtonChildInfo(
                                        icon = R.drawable.ic_create_reminder,
                                        action = AppFloatingButtonConfig.FloatingButtonAction.ADD_QUICK_REMINDER
                                    ),
                                )
                            )
                            AppFloatingButton(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd),
                                floatingButtonInfo = appFloatingButtonInfo,
                                onOptionClicked = {
                                    showQuickNoteDialog = true
                                    selectedDialogOption = it
                                }
                            )

                            QuickNoteDialog(
                                dialogItemInfo = DialogConfig.AppDialogItemInfo(
                                    title = DialogConfig.DialogTitle("Quick Text Note"),
                                    onPositiveButtonClicked = {
                                        selectedDialogOption?.let { option -> viewModel.addQuickNote(option, context) }
                                    },
                                    onNegativeButtonClicked = { showQuickNoteDialog = false }
                                ),
                                isVisible = showQuickNoteDialog,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController, defaultNavigator: DefaultNavigator) {
    NavHost(
        navController = navController,
        startDestination = SubGraphDestination.Home
    ) {
        defaultNavigator.homeFeature.registerNavigationGraph(navController, this)
        defaultNavigator.myListsFeature.registerNavigationGraph(navController, this)
        // add more destinations here.
    }
}