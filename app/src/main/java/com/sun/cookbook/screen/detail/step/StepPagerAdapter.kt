package com.sun.cookbook.screen.detail.step

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Step

class StepPagerAdapter(private val listItemStep: MutableList<Step>) :
    RecyclerView.Adapter<ItemStepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStepViewHolder {
        return ItemStepViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_step_detail, parent, false)
        )
    }

    override fun getItemCount() = listItemStep.size

    override fun onBindViewHolder(holder: ItemStepViewHolder, position: Int) {
        holder.binData(listItemStep[position])
    }
}
