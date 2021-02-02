package com.sun.cookbook.screen.detail

import com.sun.cookbook.data.model.RecipeDetail
import com.sun.cookbook.data.model.RecipeSimilar
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.remote.OnFetchDataJsonListener

class DetailPresenter(private val repository: RecipeRepository) : ViewContactDetail.Presenter {

    private var view: ViewContactDetail.View? = null

    override fun getRecipes(id: Int) {
        repository.getRecipeDetail(id, object : OnFetchDataJsonListener<RecipeDetail> {
            override fun onSuccess(data: RecipeDetail) {
                view?.getRecipeDetailSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getRecipeSimilar(id: Int) {
        repository.getRecipeSimilar(id, object : OnFetchDataJsonListener<MutableList<RecipeSimilar>> {
                override fun onSuccess(data: MutableList<RecipeSimilar>) {
                    view?.getRecipeSimilarSuccess(data)
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

    override fun setView(view: ViewContactDetail.View?) {
        this.view = view
    }
}
