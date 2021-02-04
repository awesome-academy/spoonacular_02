package com.sun.cookbook.screen.search.searchbyname

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.local.RecipeLocalDataSource
import com.sun.cookbook.data.source.remote.RecipeRemoteDataSource
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.search.searchbyname.searchrecyclerviewname.SearchRecipeByNameAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_search_by.*

class SearchByNameFragment : Fragment(), ViewContactSearchByName.View {

    private val adapter by lazy {
        SearchRecipeByNameAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    private val searchByNamePresenter: ViewContactSearchByName.Presenter by lazy {
        SearchByNamePresenter(
            RecipeRepository.getInstance(
                RecipeRemoteDataSource.instance,
                RecipeLocalDataSource.instance
            )
        )
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
            initRecyclerView()
            initPresenter()
        }
    }

    override fun searchRecipeByNameSuccess(recipes: MutableList<RecipeByIngredient>?) {
        textCountResult.text =
            context?.getString(R.string.found_i_results, recipes?.size.toString())
        textCountResult.visibility = View.VISIBLE
        adapter.updateData(recipes)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {

        searchByNamePresenter.apply {
            setView(this@SearchByNameFragment)
            searchRecipeByName(editTextSearch.text.toString())
            onStart()
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
