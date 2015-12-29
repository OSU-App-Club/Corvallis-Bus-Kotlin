package org.osuappclub.corvallisbus

import com.fasterxml.jackson.module.kotlin.*
import java.net.URL
import java.util.*

const val API_ROOT = "https://corvallisb.us/api"
/**
 * Gets the user's favorite stops, factoring in user preferences
 * and context that affect how favorites should display.
 */
public class FavoriteStopsManager {

    fun getFavoriteStops(stopIds: Array<Int>): ArrayList<FavoriteStopViewModel> {
        // TODO: figure out data type for location
        val url = URL(API_ROOT + "/favorites?stops=11776,10308&location=44.5645659,-123.2620435")
        val responseBody = url.readText()

        val mapper = jacksonObjectMapper()
        val favorites = mapper.readValue<ArrayList<FavoriteStopViewModel>>(responseBody)
        return favorites
    }
}
