package org.osuappclub.corvallisbus

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

    fun getFavoriteStops(stopIds: Array<Int>, location: Location): ArrayList<FavoriteStopViewModel> {

        val url = URL(API_ROOT + "/favorites?stops=11776,10308&location=${location.latitude},${location.longitude}")
        val responseBody = url.readText()

        val mapper = jacksonObjectMapper()
        val favorites = mapper.readValue<ArrayList<FavoriteStopViewModel>>(responseBody)
        return favorites
    }
}
