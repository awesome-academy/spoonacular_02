package com.sun.cookbook.screen.detail.nutrient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Nutrient

class NutrientAdapter : RecyclerView.Adapter<ItemNutrientViewHolder>() {

    private val nutrient = mutableListOf<Nutrient>()

    fun updateData(nutrients: MutableList<Nutrient>) {
        nutrients.let {
            nutrient.clear()
            nutrient.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNutrientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_nutrient, parent, false)
        return ItemNutrientViewHolder(itemView)
    }

    override fun getItemCount() = nutrient.size

    override fun onBindViewHolder(holder: ItemNutrientViewHolder, position: Int) {
        holder.binData(nutrient[position])
    }
}
