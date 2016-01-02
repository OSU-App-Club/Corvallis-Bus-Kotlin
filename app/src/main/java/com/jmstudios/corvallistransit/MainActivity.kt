package com.jmstudios.corvallistransit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.activity_main.*

const val PAGES_COUNT = 4

/**
 * Creates the view pager and tab layout. Container for app fragments which do more interesting things.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = "Favorites"

        pager.adapter = CorvallisBusPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(pager)

        tabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.favorites_24dp))
        tabLayout.getTabAt(1).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.ic_directions_bus_white_24dp))
        tabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.ic_warning_white_24dp))
        tabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.ic_settings_white_24dp))

        pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                toolbar.title = when (position) {
                    0 -> "Favorites"
                    1 -> "Browse"
                    2 -> "Service Alerts"
                    3 -> "Settings"
                    else -> ""
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    class CorvallisBusPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
        val pages = Array<Fragment?>(PAGES_COUNT, { null })

        override fun getItem(position: Int): Fragment? {
            if (position >= PAGES_COUNT) {
                return null
            }

            if (pages[position] == null) {
                pages[position] = createPage(position)
            }
            return pages[position]
        }

        fun createPage(position: Int): Fragment {
            when (position) {
                0 -> return FavoritesFragment()
                1 -> return SupportMapFragment.newInstance()
            }

            val fragment = BasicFragment()

            return fragment
        }

        override fun getCount(): Int = PAGES_COUNT

    }
}
