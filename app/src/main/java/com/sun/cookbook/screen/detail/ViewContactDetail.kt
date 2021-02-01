package com.sun.cookbook.screen.detail

import com.sun.cookbook.data.model.RecipeDetail
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactDetail {
    interface View {
        fun getRecipeDetailSuccess(recipes: RecipeDetail)

        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getRecipes(id: Int)
    }
}
