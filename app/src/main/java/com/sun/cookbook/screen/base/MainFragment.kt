package com.sun.cookbook.screen.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun.cookbook.R
import com.sun.cookbook.screen.main.MainMenuViewPagerAdapter
import com.sun.cookbook.utils.MenuNumber
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        containerViewpager.currentItem = MenuNumber.HOME.pageNumber
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeSearch -> containerViewpager.currentItem = MenuNumber.SEARCH.pageNumber
            R.id.homePage -> containerViewpager.currentItem = MenuNumber.HOME.pageNumber
            R.id.homeFavourite -> containerViewpager.currentItem = MenuNumber.FAVOURITE.pageNumber
        }
        return false
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bottomNavigation.menu.getItem(position).isChecked = true
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    private fun setUpViewPager() {
        containerViewpager.adapter = fragmentManager?.let {
            MainMenuViewPagerAdapter(
                it,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        }
        containerViewpager.addOnPageChangeListener(this)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
