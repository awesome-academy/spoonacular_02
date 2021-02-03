package com.sun.cookbook.screen.detail

import com.sun.cookbook.data.model.RecipeDetail
import com.sun.cookbook.data.model.RecipeSimilar
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactDetail {
    interface View {
        fun getRecipeDetailSuccess(recipes: RecipeDetail)
        fun getRecipeSimilarSuccess(recipeSimilar: MutableList<RecipeSimilar>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getRecipes(id: Int)
        fun getRecipeSimilar(id: Int)
    }
}
