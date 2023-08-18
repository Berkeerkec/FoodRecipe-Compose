package com.berkeerkec.foodrecipescompose.presentation.views.favorite_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.berkeerkec.foodrecipescompose.presentation.ui.theme.Purple40
import com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen.AnimatedShimmer
import com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen.RecipesAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(){

    Scaffold(topBar = { FavoriteAppBar() }) {

        val verticalScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(verticalScroll)
        ) {

            repeat(7){
                AnimatedShimmer()
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteAppBar(){
    TopAppBar(title = {
        Text(
            text = "Favorite",
            color = Color.White
        )
    },
        colors = TopAppBarDefaults.largeTopAppBarColors(Purple40)
    )
}



@Preview
@Composable
fun FavScreenPreview(){
    FavoriteScreen()
}