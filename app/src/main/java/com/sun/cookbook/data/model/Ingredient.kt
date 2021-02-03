package com.sun.cookbook.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
    val id: Int?,
    val name: String?,
    val image: String?,
    val amount: Double?,
    val unit: String?
) : Parcelable

object IngredientEntry {
    const val ID = "id"
    const val NAME = "name"
    const val IMAGE = "image"
    const val AMOUNT = "amount"
    const val UNIT = "unit"
}
