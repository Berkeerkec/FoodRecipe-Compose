package com.berkeerkec.foodrecipescompose.data.remote

import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries : Map<String, String>
    ) : FoodRecipe
}