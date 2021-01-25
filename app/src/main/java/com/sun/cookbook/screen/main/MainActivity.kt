package com.sun.cookbook.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun.cookbook.R
import com.sun.cookbook.utils.MenuNumber
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewPager()
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        containerViewpager.currentItem = MenuNumber.HOME.pageNumber
    }

    private fun setUpViewPager() {
        containerViewpager.adapter = MainMenuViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        containerViewpager.addOnPageChangeListener(this)
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
}
