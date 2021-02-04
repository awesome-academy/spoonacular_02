package com.sun.cookbook.screen.search.searchbyingredient

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
import com.sun.cookbook.screen.ingredient.recyclerviewingredient.RecipeByIngredientAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_ingredient_detail.*
import kotlinx.android.synthetic.main.fragment_search_by.*

class SearchByIngredientFragment : Fragment(), ViewContactSearchByIngredient.View {

    private val adapter by lazy {
        RecipeByIngredientAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    private val searchByIngredientPresenter: ViewContactSearchByIngredient.Presenter by lazy {
        SearchByIngredientPresenter(
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
        editTextSearch.hint = context?.getString(R.string.type_ingrediens)
        textSearch.setOnClickListener {
            initRecyclerView()
            initPresenter()
        }
    }

    override fun searchRecipeByIngredientSuccess(recipes: MutableList<RecipeByIngredient>?) {
        textCountResult.text =
            context?.getString(R.string.found_i_results, recipes?.size.toString())
        textCountResult.visibility = View.VISIBLE
        adapter.updateData(recipes)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {

        searchByIngredientPresenter.apply {
            setView(this@SearchByIngredientFragment)
            searchRecipeByIngredient(editTextSearch.text.toString())
            onStart()
        }
    }

    private fun initRecyclerView() {
        recyclerViewSearch.apply {
            setHasFixedSize(true)
            adapter = this@SearchByIngredientFragment.adapter
        }
    }

    companion object {

        fun newInstance() = SearchByIngredientFragment()
    }
}
