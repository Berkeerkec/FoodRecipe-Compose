package com.berkeerkec.foodrecipescompose.data.remote.dto


import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)