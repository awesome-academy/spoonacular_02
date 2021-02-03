package com.sun.cookbook.screen.detail.ingredient

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sun.cookbook.data.model.Ingredient
import com.sun.cookbook.utils.Constant
import kotlinx.android.synthetic.main.item_ingredient.view.*

class ItemIngredientViewHolder(itemView: View, private val onItemClicked: (Ingredient) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun binData(ingredient: Ingredient) {
        itemView.apply {
            Glide.with(context)
                .load(Constant.BASE_URL_IMAGE_INGREDIENT + ingredient.image)
                .into(imgIngredient)
            textIngredient.text = ingredient.name
            textAmount.text = ingredient.amount.toString() + " " + ingredient.unit

            setOnClickListener {
                onItemClicked(ingredient)
            }
        }
    }
}
