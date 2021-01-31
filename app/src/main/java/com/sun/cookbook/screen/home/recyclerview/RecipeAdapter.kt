package com.sun.cookbook.screen.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Recipe

class RecipeAdapter(private val onItemClicked: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    private val recipe = mutableListOf<Recipe>()

    fun updateData(recipes: MutableList<Recipe>?) {
        recipes?.let {
            this.recipe.clear()
            this.recipe.addAll(recipes)
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount() = recipe.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binData(recipe[position])
    }
}
