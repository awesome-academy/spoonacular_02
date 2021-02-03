package com.sun.cookbook.screen.detail.nutrient

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.data.model.Nutrient
import kotlinx.android.synthetic.main.item_nutrient.view.*

class ItemNutrientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun binData(nutrient: Nutrient) {
        itemView.apply {
            textNutrient.text = nutrient.name
            textAmount.text = nutrient.amount.toString() + nutrient.unit
        }
    }
}
