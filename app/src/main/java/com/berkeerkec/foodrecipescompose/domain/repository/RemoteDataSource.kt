package com.berkeerkec.foodrecipescompose.domain.repository

import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getRecipes(queries : Map<String,String>) : FoodRecipe
}