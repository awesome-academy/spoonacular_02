package com.sun.cookbook.screen.detail.similar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeSimilar
import com.sun.cookbook.utils.Constant
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeSimilarViewHolder(
    itemView: View,
    private val onItemClicked: (RecipeSimilar) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun binData(recipeSimilar: RecipeSimilar) {
        itemView.apply {
            Glide.with(context)
                .load(Constant.BASE_URL_IMAGE_RECIPE + recipeSimilar.id + Constant.IMAGE_RECIPE_SIMILAR)
                .into(imgDish)
            textTitleDish.text = recipeSimilar.title
            textTimeCook.text = context.getString(R.string.ready_in_minute, recipeSimilar.timeCook)
            textScore.visibility
            setOnClickListener {
                onItemClicked(recipeSimilar)
            }
        }
    }
}
