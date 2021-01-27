package com.sun.cookbook.screen.home

import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.screen.base.BasePresenter

interface ViewContactHome {

    interface View{
        fun getRecipeSlideSuccess(
            listRecipeSlide: MutableList<RecipeSlide>,
        )

        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getSliderRecipe()
    }
}
