package org.osuappclub.corvallisbus

import android.content.Context
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import org.jetbrains.anko.async
import org.jetbrains.anko.support.v4.onUiThread
import kotlinx.android.synthetic.main.favorites_row.view.*
import org.jetbrains.anko.backgroundColor
import java.util.*

/**
 * Created by rikkigibson on 12/27/15.
 */
class FavoritesFragment: ListFragment() {
    var favoritesListAdapter: FavoritesListAdapter? = null
    var googleApiClient: GoogleApiClient? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_favorites, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var builder = GoogleApiClient.Builder(context)

        // TODO: make this design better and cleaner
        builder.addConnectionCallbacks(object: GoogleApiClient.ConnectionCallbacks {
            override fun onConnected(connectionHint: Bundle?) {
                val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)

                updateFavorites(location)
            }

            override fun onConnectionSuspended(connectionHint: Int) {
                throw UnsupportedOperationException()
            }
        })
        builder.addOnConnectionFailedListener {
            // deal with a failure somehow, I guess
        }
        builder.addApi(LocationServices.API)
        googleApiClient = builder.build()

        Log.d("org.osuappclub.corvallisbus", "Created activity")
        favoritesListAdapter = FavoritesListAdapter(context, R.layout.favorites_row, ArrayList<FavoriteStopViewModel>())
        listAdapter = favoritesListAdapter

    }

    override fun onStart() {
        googleApiClient?.connect()
        super.onStart()
    }

    override fun onStop() {
        googleApiClient?.disconnect()
        super.onStop()
    }

    fun updateFavorites(location: Location) {
        async({

            // TODO: cache and reload from cache onActivityCreated
            val newFavoriteStops = FavoriteStopsManager().getFavoriteStops(arrayOf(), location)

            onUiThread {
                favoritesListAdapter?.clear()
                favoritesListAdapter?.addAll(newFavoriteStops)
                favoritesListAdapter?.notifyDataSetChanged()
            }
        })
    }
}

/**
 * Renders favorite list items based on data.
 */
class FavoritesListAdapter(context: Context, resource: Int, items: ArrayList<FavoriteStopViewModel>):
        ArrayAdapter<FavoriteStopViewModel>(context, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.favorites_row, parent, false)
        val viewModel = getItem(position)
        populateView(view, viewModel)

        return view
    }

    fun populateView(view: View, viewModel: FavoriteStopViewModel) {
        view.stopName.text = viewModel.stopName
        view.firstRouteName.text = viewModel.firstRouteName
        view.firstRouteName.backgroundColor = toRouteColor(viewModel.firstRouteColor) ?: Color.GRAY
        view.firstRouteArrivals.text = viewModel.firstRouteArrivals
        view.secondRouteName.text = viewModel.secondRouteName
        view.secondRouteName.backgroundColor = toRouteColor(viewModel.secondRouteColor) ?: Color.TRANSPARENT
        view.secondRouteArrivals.text = viewModel.secondRouteArrivals
        view.isNearestStop.visibility = if (viewModel.isNearestStop) View.VISIBLE else View.INVISIBLE
        view.distanceFromUser.text = viewModel.distanceFromUser
    }
}