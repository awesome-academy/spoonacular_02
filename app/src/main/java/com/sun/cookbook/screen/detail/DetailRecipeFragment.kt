package com.sun.cookbook.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeDetail
import com.sun.cookbook.data.model.Step
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.screen.detail.step.StepPagerAdapter
import com.sun.cookbook.utils.Constant
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import kotlinx.android.synthetic.main.fragment_detail_recipe.textTitleDish
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_recipe.*

class DetailRecipeFragment : Fragment(), ViewContactDetail.View {

    private val detailPresenter: ViewContactDetail.Presenter by lazy {
        DetailPresenter(RecipeRepository.getInstance())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        imgBack.setOnClickListener {
            fragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun getRecipeDetailSuccess(recipes: RecipeDetail) {
        Glide.with(this)
            .load(recipes.image)
            .into(imageDish)
        textTitleDish.text = recipes.title
        textTime.text = context?.getString(R.string.ready_in_minute, recipes.timeCook.toString())
        recipes.step?.run {
            initStep(this)
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        detailPresenter.apply {
            setView(this@DetailRecipeFragment)
            arguments?.getInt(BUNDLE_ID_RECIPE)?.let {
                getRecipes(it)
            }
            onStart()
        }
    }

    private fun initStep(step: MutableList<Step>) {
        viewPagerStep.adapter = StepPagerAdapter(step)
    }

    companion object {

        private const val BUNDLE_ID_RECIPE = "BUNDLE_ID_RECIPE"

        fun newInstance(idRecipe: Int) = DetailRecipeFragment().apply {
            arguments = bundleOf(BUNDLE_ID_RECIPE to idRecipe)
        }
    }
}
