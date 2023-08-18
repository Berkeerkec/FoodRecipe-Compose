package com.berkeerkec.foodrecipescompose.presentation.views.bottom_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berkeerkec.foodrecipescompose.presentation.views.favorite_screen.FavoriteScreen
import com.berkeerkec.foodrecipescompose.presentation.views.joke_Screen.JokeScreen
import com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen.RecipeScreen

@Composable
fun BottomNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Recipes.route){

        composable(route = BottomBarScreen.Recipes.route){
            RecipeScreen()
        }
        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen()
        }
        composable(route = BottomBarScreen.Joke.route){
            JokeScreen()
        }
    }
}