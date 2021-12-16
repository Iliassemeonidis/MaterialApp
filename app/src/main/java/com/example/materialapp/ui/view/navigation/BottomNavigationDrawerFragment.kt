package com.example.materialapp.ui.view.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialapp.R
import com.example.materialapp.ui.ScrollingActivity
import com.example.materialapp.ui.view.animationbonus.AnimationsBonusFragment
import com.example.materialapp.ui.view.animationenlarge.EnlargeFragment
import com.example.materialapp.ui.view.animationplanet.AnimationsFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_navigation_layout.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> {
                    openNewFragment(AnimationsFragment())
                }
                R.id.navigation_two -> {
                    openNewFragment(AnimationsBonusFragment())
                }
                R.id.navigation_three -> {
                    startActivity(Intent(activity, ScrollingActivity::class.java))
                }
                R.id.navigation_four -> {
                    openNewFragment(EnlargeFragment())
                }
            }
            dismiss()
            true
        }
    }

    private fun openNewFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}