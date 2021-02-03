package com.sun.cookbook.screen.detail.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Ingredient

class IngredientAdapter(private val onItemClicked: (Ingredient) -> Unit) :
    RecyclerView.Adapter<ItemIngredientViewHolder>() {

    private val ingredient = mutableListOf<Ingredient>()

    fun updateData(ingredients: MutableList<Ingredient>) {
        ingredients.let {
            ingredient.clear()
            ingredient.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemIngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ItemIngredientViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount() = ingredient.size

    override fun onBindViewHolder(holder: ItemIngredientViewHolder, position: Int) {
        holder.binData(ingredient[position])
    }
}
