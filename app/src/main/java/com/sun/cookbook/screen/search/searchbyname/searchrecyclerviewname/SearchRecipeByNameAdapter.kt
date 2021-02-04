package com.sun.cookbook.screen.search.searchbyname.searchrecyclerviewname

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeByIngredient

class SearchRecipeByNameAdapter(private val onItemClicked: (RecipeByIngredient) -> Unit) :
    RecyclerView.Adapter<SearchRecipeByNameViewHolder>() {

    private val recipe = mutableListOf<RecipeByIngredient>()

    fun updateData(recipes: MutableList<RecipeByIngredient>?) {
        recipes?.let {
            recipe.clear()
            recipe.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRecipeByNameViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return SearchRecipeByNameViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount() = recipe.size

    override fun onBindViewHolder(holder: SearchRecipeByNameViewHolder, position: Int) {
        holder.binData(recipe[position])
    }
}
