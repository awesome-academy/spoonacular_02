package com.sun.cookbook.screen.home.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeViewHolder(
    itemView: View,
    private val onItemClicked: (Recipe) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun binData(recipe: Recipe) {
        itemView.apply {
            Glide.with(context)
                .load(recipe.image)
                .into(imgDish)
            textTitleDish.text = recipe.title
            textTimeCook.text = context.getString(R.string.ready_in_minute, recipe.timeCook)
            textScore.text = context.getString(R.string.spoonacular_score, recipe.score.toString())
            setOnClickListener {
                onItemClicked(recipe)
            }
        }
    }
}
