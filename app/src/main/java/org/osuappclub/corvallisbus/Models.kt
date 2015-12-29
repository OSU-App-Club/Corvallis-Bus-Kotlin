package org.osuappclub.corvallisbus

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
