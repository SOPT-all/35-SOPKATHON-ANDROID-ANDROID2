package com.sopkathon.team2.presentation.model

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class Profile(
        val userId: Int
    ) : Route

    @Serializable
    data object Write : Route

    @Serializable
    data object Complete : Route
}
