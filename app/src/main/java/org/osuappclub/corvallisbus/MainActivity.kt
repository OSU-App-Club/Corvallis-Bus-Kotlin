package org.osuappclub.corvallisbus

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.activity_main.*

const val PAGES_COUNT = 4

/**
 * Creates the view pager and tab layout. Container for app fragments which do more interesting things.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = CorvallisBusPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(pager)

        // TODO: tab bar icons
        val drawable = ContextCompat.getDrawable(applicationContext, R.mipmap.ic_launcher)
        tabLayout.getTabAt(0).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.favorite))
        tabLayout.getTabAt(1).setIcon(drawable)
        tabLayout.getTabAt(2).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.ic_warning_black_24dp))
        tabLayout.getTabAt(3).setIcon(ContextCompat.getDrawable(applicationContext, R.drawable.ic_settings_black_24dp))


        val supportActionBar = supportActionBar
        // Set default title text
        supportActionBar.title = "Favorites"

        pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                supportActionBar.title = when (position) {
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
            if (position == 0) {
                return FavoritesFragment()
            }

            val fragment = BasicFragment()

            return fragment
        }

        override fun getCount(): Int = PAGES_COUNT

    }
}
