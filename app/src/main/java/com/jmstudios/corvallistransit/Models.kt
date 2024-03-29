package com.jmstudios.corvallistransit

import android.graphics.Color

/**
 * Created by rikkigibson on 12/27/15.
 */
data class FavoriteStopViewModel(
    val stopName: String,
    val stopID: Int,
    val distanceFromUser: String,
    val isNearestStop: Boolean,
    val firstRouteColor: String,
    val firstRouteName: String,
    val firstRouteArrivals: String,
    val secondRouteColor: String,
    val secondRouteName: String,
    val secondRouteArrivals: String
)

// The reason this isn't the hex literal 0xff000000 is described at this link.
// https://youtrack.jetbrains.com/issue/KT-4749
const val MAX_ALPHA = -16777216
fun toRouteColor(colorString: String): Int? {
    if (colorString.isEmpty()) {
        return null
    }

    try {
        val colorValue = Integer.parseInt(colorString, 16) + MAX_ALPHA
        return colorValue
    }
    catch(e: Exception) {
        return null
    }
}