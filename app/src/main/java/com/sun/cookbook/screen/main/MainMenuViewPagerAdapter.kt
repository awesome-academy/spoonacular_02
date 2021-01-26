package com.sun.cookbook.screen.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun.cookbook.screen.favourite.FavouriteFragment
import com.sun.cookbook.screen.home.HomeFragment
import com.sun.cookbook.screen.search.SearchFragment
import com.sun.cookbook.utils.MenuNumber

class MainMenuViewPagerAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = MENU_COUNT

    override fun getItem(position: Int): Fragment {
        return when (position) {
            MenuNumber.SEARCH.pageNumber -> SearchFragment()
            MenuNumber.HOME.pageNumber -> HomeFragment()
            else -> FavouriteFragment()
        }
    }

    companion object {
        const val MENU_COUNT = 3
    }
}
