package com.sun.cookbook.screen.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeRecyclerView

class HomeRecyclerViewAdapter(private val onItemClicked: (RecipeRecyclerView) -> Unit) :
    RecyclerView.Adapter<HomeRecyclerViewViewHolder>() {

    private val recipeRecyclerView = mutableListOf<RecipeRecyclerView>()

    fun updateData(movies: MutableList<RecipeRecyclerView>?) {
        movies?.let {
            this.recipeRecyclerView.clear()
            this.recipeRecyclerView.addAll(movies)
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return HomeRecyclerViewViewHolder(itemView, onItemClicked)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewViewHolder, position: Int) {
        holder.binData(recipeRecyclerView[position])
    }

    override fun getItemCount() = recipeRecyclerView.size
}