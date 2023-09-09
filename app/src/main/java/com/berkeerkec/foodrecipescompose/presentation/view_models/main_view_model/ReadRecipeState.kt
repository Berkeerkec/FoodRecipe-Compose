package com.berkeerkec.foodrecipescompose.presentation.view_models.main_view_model

import com.berkeerkec.foodrecipescompose.data.local.RecipesEntity

data class ReadRecipeState(
    val entity : RecipesEntity? = null
)