package com.sun.cookbook.screen.home

import com.sun.cookbook.data.model.Recipe
import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

class HomePresenter(private val repository: RecipeRepository) : ViewContactHome.Presenter {

    private var view: ViewContactHome.View? = null

    override fun onStart() {
        getSliderRecipe()
        getRecipes()
    }

    override fun onStop() {
        view = null
    }

    override fun setView(view: ViewContactHome.View?) {
        this.view = view
    }

    override fun getSliderRecipe() {
        repository.getRecipeSlide(object : OnFetchDataJsonListener<MutableList<RecipeSlide>> {
            override fun onSuccess(data: MutableList<RecipeSlide>) {
                view?.getRecipeSlideSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getRecipes() {
        repository.getRecipe(object :
            OnFetchDataJsonListener<MutableList<Recipe>> {
            override fun onSuccess(data: MutableList<Recipe>) {
                view?.getRecipeSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }
}
