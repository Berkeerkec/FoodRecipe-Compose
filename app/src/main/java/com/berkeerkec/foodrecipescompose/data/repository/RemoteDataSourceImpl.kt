package com.berkeerkec.foodrecipescompose.data.repository

import com.berkeerkec.foodrecipescompose.data.remote.FoodRecipesApi
import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import com.berkeerkec.foodrecipescompose.domain.repository.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api : FoodRecipesApi
) : RemoteDataSource{
    override suspend fun getRecipes(queries: Map<String, String>): FoodRecipe {
        return api.getRecipes(queries)
    }
}