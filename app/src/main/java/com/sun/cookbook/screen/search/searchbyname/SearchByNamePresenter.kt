package com.sun.cookbook.screen.search.searchbyname

import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

class SearchByNamePresenter(private val repository: RecipeRepository) :
    ViewContactSearchByName.Presenter {

    private var view: ViewContactSearchByName.View? = null

    override fun searchRecipeByName(name: String) {
        repository.searchRecipeByName(name, object :
            OnFetchDataJsonListener<MutableList<RecipeByIngredient>> {
            override fun onSuccess(data: MutableList<RecipeByIngredient>) {
                view?.searchRecipeByNameSuccess(data)
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

    override fun setView(view: ViewContactSearchByName.View?) {
        this.view = view
    }
}
