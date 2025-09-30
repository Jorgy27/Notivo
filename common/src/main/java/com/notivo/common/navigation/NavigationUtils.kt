package com.notivo.common.navigation

import kotlinx.serialization.Serializable

/**Represents navigation subgraphs — logical groups of screens under a common root. */
sealed class SubGraphDestination{

    @Serializable
    data object Home: SubGraphDestination()

    @Serializable
    data object MyLists: SubGraphDestination()
}

/**Represents leaf destinations (screens) inside a subgraph.
 * Each Destination corresponds to an actual composable screen*/
sealed class Destination{

    @Serializable
    data object Home: Destination()

    @Serializable
    data object MyLists: Destination()
}