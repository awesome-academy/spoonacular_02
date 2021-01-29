package com.sun.cookbook.screen.home.slide

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeSlide
import kotlinx.android.synthetic.main.fragment_home_slide.view.*

class ItemSlideViewHolder(itemView: View, private val onSlideClicked: (RecipeSlide) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun binData(recipeSlide: RecipeSlide) {
        itemView.apply {
            Glide.with(context).load(recipeSlide.image)
                .into(imageSlide)
            textTitleDish.text = recipeSlide.title
            textTimeCook.text = context.getString(R.string.ready_in_minute,recipeSlide.timeCook)
            setOnClickListener {
                onSlideClicked.invoke(recipeSlide)
            }
        }
    }
}
