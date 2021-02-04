package com.sun.cookbook.screen.search.searchbyname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.cookbook.R
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.search.searchbyname.searchrecyclerviewname.SearchRecipeByNameAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_search_by.*

class SearchByNameFragment : Fragment() {

    private val adapter by lazy {
        SearchRecipeByNameAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_by, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textSearch.setOnClickListener {
            textCountResult.visibility = View.VISIBLE
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        recyclerViewSearch.apply {
            setHasFixedSize(true)
            adapter = this@SearchByNameFragment.adapter
        }
    }

    companion object {

        fun newInstance() = SearchByNameFragment()
    }
}
