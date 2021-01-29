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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeRecyclerView
import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.data.source.RecipeRepository
import com.sun.cookbook.screen.detail.DetailRecipeFragment
import com.sun.cookbook.screen.home.recyclerview.HomeRecyclerViewAdapter
import com.sun.cookbook.screen.home.slide.HomeSlideAdapter
import com.sun.cookbook.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), ViewContactHome.View {
    private var recipeSlide = mutableListOf<RecipeSlide>()
    private var recipeRecyclerView = mutableListOf<RecipeRecyclerView>()
    private var getCurrentItem = 0
    private var isChecked = false
    private val homePresenter: ViewContactHome.Presenter by lazy {
        HomePresenter(RecipeRepository.getInstance())
    }
    private val adapter: HomeRecyclerViewAdapter by lazy {
        HomeRecyclerViewAdapter {
            replaceFragment(DetailRecipeFragment.instance(), R.id.mainContainer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun getRecipeSlideSuccess(listRecipeSlide: MutableList<RecipeSlide>) {
        recipeSlide = listRecipeSlide
        viewPagerSlideHome?.let { initSlideAdapter(viewPagerSlideHome) }
    }

    override fun getRecipeRecyclerViewSuccess(listRecipeRecyclerView: MutableList<RecipeRecyclerView>) {
        recipeRecyclerView = listRecipeRecyclerView
        adapter.updateData(recipeRecyclerView)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initView()
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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    @SuppressLint("WrongConstant")
    private fun initSlideAdapter(applyViewPageSlider: ViewPager2) {
        applyViewPageSlider.apply {
            adapter = HomeSlideAdapter(recipeSlide, applyViewPageSlider) {
                replaceFragment(DetailRecipeFragment.instance(), R.id.mainContainer)
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
