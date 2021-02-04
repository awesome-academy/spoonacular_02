package com.sun.cookbook.screen.ingredient

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
import com.sun.cookbook.data.model.Ingredient
import com.sun.cookbook.data.model.RecipeByIngredient
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.ingredient.recyclerviewingredient.RecipeByIngredientAdapter
import com.sun.cookbook.utils.Constant
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import kotlinx.android.synthetic.main.fragment_ingredient_detail.*
import kotlinx.android.synthetic.main.fragment_ingredient_detail.imgBack
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientDetailFragment : Fragment(), ViewContactDetailIngredient.View {

    private val ingredientDetailPresenter: ViewContactDetailIngredient.Presenter by lazy {
        IngredientDetailPresenter(RecipeRepository.getInstance())
    }

    private val adapter by lazy {
        RecipeByIngredientAdapter() {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ingredient_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgBack.setOnClickListener {
            fragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        initRecyclerView()
        initPresenter()
    }

    override fun getRecipeByIngredientSuccess(recipes: MutableList<RecipeByIngredient>) {
        adapter.updateData(recipes)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        ingredientDetailPresenter.apply {
            setView(this@IngredientDetailFragment)
            arguments?.getString(BUNDLE_NAME_INGREDIENT)?.let {
                getRecipeByIngredient(it)
                textNameIngredient.text = it
            }
            arguments?.getString(BUNDLE_IMAGE_INGREDIENT)?.let {
                Glide.with(this@IngredientDetailFragment)
                    .load(Constant.BASE_URL_IMAGE_INGREDIENT + it)
                    .into(imgIngredient)
            }
            onStart()
        }
    }

    private fun initRecyclerView() {
        recyclerIngredientDetail.apply {
            setHasFixedSize(true)
            adapter = this@IngredientDetailFragment.adapter
        }
    }

    companion object {

        private const val BUNDLE_NAME_INGREDIENT = "BUNDLE_NAME_INGREDIENT"
        private const val BUNDLE_IMAGE_INGREDIENT = "BUNDLE_IMAGE_INGREDIENT"

        fun newInstance(ingredient: Ingredient) = IngredientDetailFragment().apply {
            arguments = bundleOf(
                BUNDLE_NAME_INGREDIENT to ingredient.name,
                BUNDLE_IMAGE_INGREDIENT to ingredient.image)
            }
        }
    }
