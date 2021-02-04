package com.sun.cookbook.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.cookbook.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        tabLayoutSearch.setupWithViewPager(viewPagerSearch)
        viewPagerSearch.adapter = fragmentManager?.let {
            SearchViewPagerAdapter(it)
        }
    }

    companion object {

        fun newInstance() = SearchFragment()
    }
}
