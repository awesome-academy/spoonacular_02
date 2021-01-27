package com.sun.cookbook.data.source.local

import com.sun.cookbook.data.source.RecipeDataSource

class RecipeLocalDataSource : RecipeDataSource.Local {

    private object Holder {
        val INSTANCE = RecipeLocalDataSource()
    }

    companion object {
        val instance:RecipeLocalDataSource by lazy { Holder.INSTANCE }
    }
}
