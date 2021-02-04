package com.sun.cookbook.screen.ingredient.recyclerviewingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeByIngredient

class RecipeByIngredientAdapter(private val onItemClicked: (RecipeByIngredient) -> Unit) :
    RecyclerView.Adapter<RecipeByIngredientViewHolder>() {

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
    ): RecipeByIngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeByIngredientViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount() = recipe.size

    override fun onBindViewHolder(holder: RecipeByIngredientViewHolder, position: Int) {
        holder.binData(recipe[position])
    }
}
