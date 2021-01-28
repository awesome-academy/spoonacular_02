package com.sun.cookbook.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sun.cookbook.R
import com.sun.cookbook.data.model.RecipeSlide
import com.sun.cookbook.data.source.RecipeRepository

class HomeFragment : Fragment(), ViewContactHome.View {

    private val homePresenter: ViewContactHome.Presenter by lazy {
        HomePresenter(RecipeRepository.getInstance())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun getRecipeSlideSuccess(listRecipeSlide: MutableList<RecipeSlide>) {
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
    }

    private fun initPresenter() {
        homePresenter.apply {
            setView(this@HomeFragment)
            onStart()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
