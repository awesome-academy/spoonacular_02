package com.sun.cookbook.screen.detail.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Ingredient
import com.sun.cookbook.screen.ingredient.IngredientDetailFragment
import com.sun.cookbook.screen.main.MainActivity
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_ingredient.*

class IngredientFragment : BottomSheetDialogFragment() {

    private val adapter by lazy {
        IngredientAdapter {
            (activity as? MainActivity)?.addFragment(
                IngredientDetailFragment.newInstance(it),
                R.id.mainContainer
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelableArrayList<Ingredient>(BUNDLE_LIST_INGREDIENT)?.let {
            adapter.updateData(it)
        }
        initRecyclerViewIngredient()
    }

    private fun initRecyclerViewIngredient() {
        recyclerViewBottomSheet?.apply {
            setHasFixedSize(true)
            adapter = this@IngredientFragment.adapter
        }
    }

    companion object {

        private const val BUNDLE_LIST_INGREDIENT = "BUNDLE_LIST_INGREDIENT"

        fun newInstance(listIngredient: ArrayList<Ingredient>) = IngredientFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(BUNDLE_LIST_INGREDIENT, listIngredient)
            }
        }
    }
}
