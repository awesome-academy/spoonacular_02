package com.sun.cookbook.screen.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sun.cookbook.screen.search.searchbyname.SearchByNameFragment
import com.sun.cookbook.screen.search.searchbyingredient.SearchByIngredientFragment
import com.sun.cookbook.utils.TypeSearch

class SearchViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = TypeSearch.values().size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TypeSearch.BY_NAME.ordinal -> SearchByNameFragment.newInstance()
            else -> SearchByIngredientFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            TypeSearch.BY_NAME.ordinal -> TypeSearch.BY_NAME.searchType
            else -> TypeSearch.BY_INGREDIENT.searchType
        }
    }
}
