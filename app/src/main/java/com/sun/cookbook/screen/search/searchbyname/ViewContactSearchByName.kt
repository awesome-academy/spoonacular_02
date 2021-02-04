package com.sun.cookbook.screen.search.searchbyname

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactSearchByName {
    interface View {
        fun searchRecipeByNameSuccess(recipes: MutableList<RecipeByIngredient>?)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun searchRecipeByName(name: String)
    }
}
