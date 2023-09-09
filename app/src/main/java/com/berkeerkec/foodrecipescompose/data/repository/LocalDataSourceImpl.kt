package com.berkeerkec.foodrecipescompose.data.repository

import com.berkeerkec.foodrecipescompose.data.local.RecipesDao
import com.berkeerkec.foodrecipescompose.data.local.RecipesEntity
import com.berkeerkec.foodrecipescompose.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao : RecipesDao
) : LocalDataSource {
    override fun readDatabase(): Flow<RecipesEntity> {
        return dao.readRecipes()
    }

    override suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        return dao.insertRecipes(recipesEntity)
    }
}