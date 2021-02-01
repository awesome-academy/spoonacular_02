package com.sun.cookbook.screen.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sun.cookbook.R
import com.sun.cookbook.data.model.Recipe
import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.home.recyclerview.RecipeAdapter
import com.sun.cookbook.screen.home.slide.HomeSlideAdapter
import com.sun.cookbook.utils.addFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ViewContactHome.View {

    private var recipeSlide = mutableListOf<RecipeSlide>()
    private var getCurrentItem = 0
    private var isChecked = false
    private val homePresenter: ViewContactHome.Presenter by lazy {
        HomePresenter(RecipeRepository.getInstance())
    }
    private val adapter by lazy {
        RecipeAdapter {
            addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initPresenter()
    }

    override fun getRecipeSlideSuccess(listRecipeSlide: MutableList<RecipeSlide>) {
        recipeSlide = listRecipeSlide
        viewPagerSlideHome?.let { initSlideAdapter(viewPagerSlideHome) }
    }

    override fun getRecipeSuccess(recipes: MutableList<Recipe>) {
        adapter.updateData(recipes)
    }

    override fun onError(exception: Exception?) {
        if (exception == null) Toast.makeText(context, null, Toast.LENGTH_SHORT).show()
        else Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        homePresenter.apply {
            setView(this@HomeFragment)
            onStart()
        }
    }

    private fun initView() {
        recyclerViewHome.apply {
            setHasFixedSize(true)
            adapter = this@HomeFragment.adapter
        }
    }

    @SuppressLint("WrongConstant")
    private fun initSlideAdapter(applyViewPageSlider: ViewPager2) {
        applyViewPageSlider.apply {
            adapter = HomeSlideAdapter(recipeSlide) {
                addFragment(DetailRecipeFragment.newInstance(it.id), R.id.mainContainer)
            }
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
            getChildAt(GET_ITEM_START).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val handlerSlider = Looper.myLooper()?.let { Handler(it) }
                    super.onPageSelected(position)
                    handlerSlider?.postDelayed({
                        when (getCurrentItem) {
                            (recipeSlide.size - ITEM_NUMBER) -> isChecked = false
                            GET_ITEM_START -> isChecked = true
                        }
                        getCurrentItem =
                            if (isChecked) getCurrentItem + ITEM_NUMBER else getCurrentItem - ITEM_NUMBER
                        viewPagerSlideHome?.currentItem = getCurrentItem
                    }, DELAY_NEXT_ITEM)
                }
            }
            )
        }
    }

    companion object {

        fun newInstance() = HomeFragment()

        private const val OFF_SCREEN_PAGE_LIMIT = 3
        private const val DELAY_NEXT_ITEM = 2000L
        private const val GET_ITEM_START = 0
        private const val ITEM_NUMBER = 1
    }
}
