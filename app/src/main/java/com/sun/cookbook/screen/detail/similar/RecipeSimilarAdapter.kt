package com.sun.cookbook.screen.detail.similar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeSimilar

class RecipeSimilarAdapter(private val onItemClicked: (RecipeSimilar) -> Unit) :
    RecyclerView.Adapter<RecipeSimilarViewHolder>() {

    private val recipeSimilar = mutableListOf<RecipeSimilar>()

    fun updateData(recipeSimilars: MutableList<RecipeSimilar>?) {
        recipeSimilars?.let {
            this.recipeSimilar.clear()
            this.recipeSimilar.addAll(recipeSimilars)
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeSimilarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeSimilarViewHolder(itemView, onItemClicked)
    }

    override fun getItemCount() = recipeSimilar.size

    override fun onBindViewHolder(holder: RecipeSimilarViewHolder, position: Int) {
        holder.binData(recipeSimilar[position])
    }
}
