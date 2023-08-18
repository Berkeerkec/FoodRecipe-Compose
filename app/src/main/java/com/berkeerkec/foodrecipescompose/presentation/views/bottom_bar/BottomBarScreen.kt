package com.berkeerkec.foodrecipescompose.presentation.views.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.berkeerkec.foodrecipescompose.R

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : Int
){
    object Recipes : BottomBarScreen(
        route = "recipes",
        title = "Recipes",
        icon = R.drawable.ic_recipe
    )
    object Favorite : BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = R.drawable.ic_fav
    )
    object Joke : BottomBarScreen(
        route = "joke",
        title = "Joke",
        icon = R.drawable.ic_jokee
    )
}
