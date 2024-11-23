package com.sopkathon.team2.presentation.model

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object Write : Route

    @Serializable
    data object Complete : Route
}
