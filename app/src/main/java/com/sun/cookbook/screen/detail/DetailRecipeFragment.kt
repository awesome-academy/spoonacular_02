package com.sun.cookbook.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeDetail

class DetailRecipeFragment : Fragment(), ViewContactDetail.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_recipe, container, false)
    }

    override fun getRecipeDetailSuccess(recipes: RecipeDetail) {
    }

    override fun onError(exception: Exception?) {
    }

    companion object {
        private const val BUNDLE_ID_RECIPE = "BUNDLE_ID_RECIPE"

        fun newInstance(idRecipe: Int) = DetailRecipeFragment().apply {
            arguments = bundleOf(BUNDLE_ID_RECIPE to idRecipe)
        }
    }
}
