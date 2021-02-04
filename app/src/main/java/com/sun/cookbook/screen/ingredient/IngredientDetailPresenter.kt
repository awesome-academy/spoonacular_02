package com.sun.cookbook.screen.ingredient

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

class IngredientDetailPresenter(private val repository: RecipeRepository) :
    ViewContactDetailIngredient.Presenter {

    private var view: ViewContactDetailIngredient.View? = null

    override fun getRecipeByIngredient(name: String) {
        repository.getRecipeByIngredient(name, object : OnFetchDataJsonListener<MutableList<RecipeByIngredient>> {
                override fun onSuccess(data: MutableList<RecipeByIngredient>) {
                    view?.getRecipeByIngredientSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            })
    }

    override fun onStart() {
    }

    override fun onStop() {
        view = null
    }

    override fun setView(view: ViewContactDetailIngredient.View?) {
        this.view = view
    }
}
