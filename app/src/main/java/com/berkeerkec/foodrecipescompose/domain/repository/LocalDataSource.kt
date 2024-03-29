package com.berkeerkec.foodrecipescompose.domain.repository

import com.berkeerkec.foodrecipescompose.data.local.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun readDatabase() : Flow<RecipesEntity>

    suspend fun insertRecipes(recipesEntity: RecipesEntity)
}