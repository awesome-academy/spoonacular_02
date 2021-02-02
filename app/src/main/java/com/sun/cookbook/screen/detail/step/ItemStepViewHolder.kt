package com.sun.cookbook.screen.detail.step

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Step
import kotlinx.android.synthetic.main.item_step_detail.view.*

class ItemStepViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun binData(steps: Step) {
        itemView.apply {
            textNumberStep.text = context.getString(R.string.step, steps.number.toString())
            textStep.text = "''" + steps.step + "''"
        }
    }
}
