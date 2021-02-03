package com.sun.cookbook.screen.detail.nutrient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Nutrient
import kotlinx.android.synthetic.main.fragment_bottom_sheet_nutrient.*

class NutrientFragment : BottomSheetDialogFragment() {

    private val adapter by lazy {
        NutrientAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_nutrient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelableArrayList<Nutrient>(BUNDLE_LIST_NUTRIENT)?.let {
            adapter.updateData(it)
        }
        initRecyclerViewNutrient()
    }

    private fun initRecyclerViewNutrient() {
        recyclerViewBottomSheetNutrient?.apply {
            setHasFixedSize(true)
            adapter = this@NutrientFragment.adapter
        }
    }

    companion object {

        private const val BUNDLE_LIST_NUTRIENT = "BUNDLE_LIST_NUTRIENT"

        fun newInstance(listNutrient: ArrayList<Nutrient>) = NutrientFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(BUNDLE_LIST_NUTRIENT, listNutrient)
            }
        }
    }
}
