package com.sun.cookbook.data.model

data class Nutrient(
    val name: String?,
    val amount: Double?,
    val unit: String?
)

object NutrientEntry {
    const val NAME = "name"
    const val AMOUNT = "amount"
    const val UNIT = "unit"
}
