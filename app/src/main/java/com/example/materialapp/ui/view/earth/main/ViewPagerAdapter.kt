package com.example.materialapp.ui.view.earth.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materialapp.ui.view.earth.planets.EarthFragment
import com.example.materialapp.ui.view.earth.planets.MarsFragment
import com.example.materialapp.ui.view.earth.planets.WeatherFragment

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val WEATHER_FRAGMENT = 2
private const val EARTH = "Earth"
private const val MARS = "Mars"
private const val WEATHER = "Weather"


class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragments = arrayOf(EarthFragment(), MarsFragment(), WeatherFragment())
    private val mapPlanet: Map<Int,String> = mapOf(Pair(EARTH_FRAGMENT, EARTH),Pair(MARS_FRAGMENT, MARS),Pair(
        WEATHER_FRAGMENT, WEATHER
    ))

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[WEATHER_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }

    operator fun get(position: Int) = mapPlanet[position]

}