package com.notivo

import com.notivo.home.navigation.HomeFeature
import com.notivo.mylists.navigation.MyListsFeature

data class DefaultNavigator(
    val homeFeature: HomeFeature,
    val myListsFeature: MyListsFeature
)