package com.sun.cookbook.screen.home.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeRecyclerView
import kotlinx.android.synthetic.main.item_recipe.view.*

class HomeRecyclerViewViewHolder(
    itemView: View,
    private val onItemClicked: (RecipeRecyclerView) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    fun binData(recipeRecyclerView: RecipeRecyclerView) {
        itemView.apply {
            Glide.with(context).load(recipeRecyclerView.image)
                .into(imgDish)
            textTitleDish.text = recipeRecyclerView.title
            textTimeCook.text = context.getString(R.string.ready_in_minute, recipeRecyclerView.timeCook)
            textScore.text = context.getString(R.string.spoonacular_score,recipeRecyclerView.score.toString())
            setOnClickListener {
                onItemClicked.invoke(recipeRecyclerView)
            }
        }
    }
}
