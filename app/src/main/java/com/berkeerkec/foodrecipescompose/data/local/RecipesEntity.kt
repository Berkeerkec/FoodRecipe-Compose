package com.berkeerkec.foodrecipescompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import com.berkeerkec.foodrecipescompose.util.Constant.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe : FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}