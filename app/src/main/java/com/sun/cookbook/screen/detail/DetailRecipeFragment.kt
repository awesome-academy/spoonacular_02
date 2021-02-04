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
import com.sun.cookbook.data.model.RecipeSimilar
import com.sun.cookbook.data.model.Step
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.data.source.local.RecipeLocalDataSource
import com.sun.cookbook.data.source.remote.RecipeRemoteDataSource
import com.sun.cookbook.screen.detail.ingredient.IngredientFragment
import com.sun.cookbook.screen.detail.nutrient.NutrientFragment
import com.sun.cookbook.screen.detail.similar.RecipeSimilarAdapter
import com.sun.cookbook.screen.detail.step.StepPagerAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import kotlinx.android.synthetic.main.fragment_detail_recipe.textTitleDish

class DetailRecipeFragment : Fragment(), ViewContactDetail.View {

    private val detailPresenter: ViewContactDetail.Presenter by lazy {
        DetailPresenter(
            RecipeRepository.getInstance(
                RecipeRemoteDataSource.instance,
                RecipeLocalDataSource.instance
            )
        )
    }
    private val adapter by lazy {
        RecipeSimilarAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSimilarRecyclerViewAdapter()
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
        recipes.step?.let(::initStep)
        textIngredient.setOnClickListener {
            recipes.ingredient?.let {
                IngredientFragment.newInstance(it).show(childFragmentManager, null)
            }
        }
        textNutrient.setOnClickListener {
            recipes.nutrient?.let {
                NutrientFragment.newInstance(it).show(childFragmentManager, null)
            }
        }
    }

    override fun getRecipeSimilarSuccess(recipeSimilar: MutableList<RecipeSimilar>) {
        recipeSimilar.let {
            adapter.updateData(it)
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
                getRecipeSimilar(it)
            }
            onStart()
        }
    }

    private fun initStep(step: MutableList<Step>) {
        viewPagerStep.adapter = StepPagerAdapter(step)
    }

    private fun initSimilarRecyclerViewAdapter() {
        recyclerViewSimilarRecipe.apply {
            setHasFixedSize(true)
            adapter = this@DetailRecipeFragment.adapter
        }
    }

    companion object {

        private const val BUNDLE_ID_RECIPE = "BUNDLE_ID_RECIPE"

        fun newInstance(idRecipe: Int?) = DetailRecipeFragment().apply {
            arguments = bundleOf(BUNDLE_ID_RECIPE to idRecipe)
        }
    }
}
