package com.notivo.common.navigation

import kotlinx.serialization.Serializable

sealed class SubGraphDestination{

    @Serializable
    data object Home: SubGraphDestination()
}

sealed class Destination{

    @Serializable
    data object Home: Destination()
}