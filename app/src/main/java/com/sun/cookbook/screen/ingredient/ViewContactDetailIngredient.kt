package com.sun.cookbook.screen.ingredient

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactDetailIngredient {

    interface View {
        fun getRecipeByIngredientSuccess(recipes: MutableList<RecipeByIngredient>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getRecipeByIngredient(name: String)
    }
}
