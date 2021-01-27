package com.sun.cookbook.screen.home.slide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeSlide

class HomeSlideAdapter(
    private val listItemSlide: MutableList<RecipeSlide>,
    applyViewPageSlider: ViewPager2,
    private val onSlideClicked: (RecipeSlide) -> Unit
) : RecyclerView.Adapter<ItemSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSlideViewHolder {
        return ItemSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_slide, parent, false),
            onSlideClicked
        )
    }

    override fun onBindViewHolder(holder: ItemSlideViewHolder, position: Int) {
        holder.binData(listItemSlide[position])
    }

    override fun getItemCount() = listItemSlide.size
}
