package com.sun.cookbook.screen.ingredient.recyclerviewingredient

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.data.model.RecipeByIngredient
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeByIngredientViewHolder(
    itemView: View,
    private val onItemClicked: (RecipeByIngredient) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun binData(recipe: RecipeByIngredient) {
        itemView.apply {
            Glide.with(context)
                .load(recipe.image)
                .into(imgDish)
            textTitleDish.text = recipe.title
            setOnClickListener {
                onItemClicked(recipe)
            }
        }
    }
}
