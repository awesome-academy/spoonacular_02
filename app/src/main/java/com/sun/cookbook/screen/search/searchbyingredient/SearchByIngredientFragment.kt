package com.sun.cookbook.screen.search.searchbyingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.cookbook.R
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.ingredient.recyclerviewingredient.RecipeByIngredientAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_ingredient_detail.*
import kotlinx.android.synthetic.main.fragment_search_by.*

class SearchByIngredientFragment : Fragment(){

    private val adapter by lazy {
        RecipeByIngredientAdapter {
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
        editTextSearch.hint = context?.getString(R.string.type_ingrediens)
        textSearch.setOnClickListener {
            textCountResult.visibility = View.VISIBLE
            initRecyclerView()
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
