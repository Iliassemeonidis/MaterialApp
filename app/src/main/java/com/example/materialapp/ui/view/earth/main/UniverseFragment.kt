package com.example.materialapp.ui.view.earth.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialapp.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_universe.*
import me.relex.circleindicator.CircleIndicator3


class UniverseFragment : Fragment() {

    private lateinit var adapter: ViewPagerAdapter
    private val mapEarth: Map<Int, Int> =
        mapOf(
            Pair(0, R.drawable.ic_earth), Pair(1, R.drawable.ic_mars),
            Pair(2, R.drawable.ic_system)
        )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
    }

    private fun initPager() {
        adapter = ViewPagerAdapter(requireActivity())
        view_pager.adapter = adapter

        createIndicatorAndInitTable()
    }

    private fun createIndicatorAndInitTable() {
        val indicator: CircleIndicator3 = indicator
        indicator.setViewPager(view_pager)

        initTable(indicator)
    }

    private fun initTable(indicator: CircleIndicator3) {
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = adapter[position]
            tab.setIcon(mapEarth[position] ?: 0)
        }.attach()
    }
}