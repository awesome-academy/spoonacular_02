package com.sun.cookbook.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nutrient(
    val name: String?,
    val amount: Double?,
    val unit: String?
) : Parcelable

object NutrientEntry {
    const val NAME = "name"
    const val AMOUNT = "amount"
    const val UNIT = "unit"
}
