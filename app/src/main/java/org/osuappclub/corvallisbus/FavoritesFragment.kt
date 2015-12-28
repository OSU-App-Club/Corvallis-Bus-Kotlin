package org.osuappclub.corvallisbus

import android.content.Context
import android.database.DataSetObserver
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * Created by rikkigibson on 12/27/15.
 */
class FavoritesFragment: ListFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_favorites, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAdapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listOf("foo", "bar", "baz"))
    }
}

class FavoritesListAdapter: ListAdapter {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        throw UnsupportedOperationException()
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        throw UnsupportedOperationException()
    }

    override fun getItemViewType(position: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun getItem(position: Int): Any? {
        throw UnsupportedOperationException()
    }

    override fun getViewTypeCount(): Int {
        throw UnsupportedOperationException()
    }

    override fun getItemId(position: Int): Long {
        throw UnsupportedOperationException()
    }

    override fun hasStableIds(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        throw UnsupportedOperationException()
    }

    override fun getCount(): Int {
        throw UnsupportedOperationException()
    }

    override fun isEnabled(position: Int): Boolean {
        throw UnsupportedOperationException()
    }

    override fun areAllItemsEnabled(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException()
    }

}