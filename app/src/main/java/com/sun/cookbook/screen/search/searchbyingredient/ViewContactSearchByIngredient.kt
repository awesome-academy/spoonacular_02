package com.sun.cookbook.screen.search.searchbyingredient

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactSearchByIngredient {
    interface View {
        fun searchRecipeByIngredientSuccess(recipes: MutableList<RecipeByIngredient>?)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun searchRecipeByIngredient(ingredients: String)
    }
}
