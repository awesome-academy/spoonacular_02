package com.sun.cookbook.screen.search.searchbyname.searchrecyclerviewname

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.utils.Constant
import kotlinx.android.synthetic.main.item_recipe.view.*

class SearchRecipeByNameViewHolder(
    itemView: View,
    private val onItemClicked: (RecipeByIngredient) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun binData(recipe: RecipeByIngredient) {
        itemView.apply {
            Glide.with(context)
                .load(Constant.BASE_URL_IMAGE_RECIPE + recipe.image)
                .into(imgDish)
            textTitleDish.text = recipe.title
            setOnClickListener {
                onItemClicked(recipe)
            }
        }
    }
}
