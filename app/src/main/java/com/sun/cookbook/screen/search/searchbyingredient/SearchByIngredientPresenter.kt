package com.sun.cookbook.screen.search.searchbyingredient

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

class SearchByIngredientPresenter(private val repository: RecipeRepository) :
    ViewContactSearchByIngredient.Presenter {

    private var view: ViewContactSearchByIngredient.View? = null

    override fun searchRecipeByIngredient(ingredients: String) {
        repository.searchRecipeByIngredient(ingredients, object : OnFetchDataJsonListener<MutableList<RecipeByIngredient>>{
            override fun onSuccess(data: MutableList<RecipeByIngredient>) {
                view?.searchRecipeByIngredientSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {}

    override fun onStop() {
        view = null
    }

    override fun setView(view: ViewContactSearchByIngredient.View?) {
        this.view = view
    }
}
