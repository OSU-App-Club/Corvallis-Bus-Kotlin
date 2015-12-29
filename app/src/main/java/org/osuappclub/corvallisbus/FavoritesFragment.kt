package org.osuappclub.corvallisbus

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.support.v4.onUiThread
import java.util.*

/**
 * Created by rikkigibson on 12/27/15.
 */
class FavoritesFragment: ListFragment() {
    var favoritesListAdapter: ArrayAdapter<String>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_favorites, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("org.osuappclub.corvallisbus", "Created activity")
        favoritesListAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ArrayList<String>())
        listAdapter = favoritesListAdapter

        updateFavorites()
    }

    fun updateFavorites() {
        async({
            // TODO: cache and reload from cache onActivityCreated
            val newFavoriteStops = FavoriteStopsManager().getFavoriteStops(arrayOf())

            onUiThread {
                favoritesListAdapter?.clear()
                favoritesListAdapter?.addAll(newFavoriteStops.map({ it.stopName }))
                favoritesListAdapter?.notifyDataSetChanged()
            }
        })
    }
}

/**
 * Renders favorite list items based on data.
 */
class FavoritesListAdapter(context: Context, resource: Int): ArrayAdapter<FavoriteStopViewModel>(context, resource, arrayOf()) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return super.getView(position, convertView, parent)
    }
}