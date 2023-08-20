package com.berkeerkec.foodrecipescompose.presentation.view_models.main_view_model

import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe

data class GetRecipeState(
    val isLoading : Boolean = false,
    val recipes : FoodRecipe? = null,
    val error : String = ""
)
