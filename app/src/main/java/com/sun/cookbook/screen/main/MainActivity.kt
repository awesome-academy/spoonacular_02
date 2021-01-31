package com.sun.cookbook.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.cookbook.R
import com.sun.cookbook.screen.base.MainFragment
import com.sun.cookbook.utils.addFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MainFragment.newInstance(),R.id.mainContainer)
    }
}
