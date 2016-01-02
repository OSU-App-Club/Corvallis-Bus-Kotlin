package com.jmstudios.corvallistransit

import android.location.Location
import com.fasterxml.jackson.module.kotlin.*
import java.net.URL
import java.util.*

const val API_ROOT = "https://corvallisb.us/api"
/**
 * Gets the user's favorite stops, factoring in user preferences
 * and context that affect how favorites should display.
 */
public class FavoriteStopsManager {

    fun getFavoriteStops(stopIds: Array<Int>, location: Location?): ArrayList<FavoriteStopViewModel> {
        val stopsString = stopIds.joinToString(",")
        val locationString = if (location == null) "" else "${location.latitude},${location.longitude}"
        val url = URL("$API_ROOT/favorites?stops=$stopsString&location=$locationString")
        val responseBody = url.readText()

        val mapper = jacksonObjectMapper()
        val favorites = mapper.readValue<ArrayList<FavoriteStopViewModel>>(responseBody)
        return favorites
    }
}
