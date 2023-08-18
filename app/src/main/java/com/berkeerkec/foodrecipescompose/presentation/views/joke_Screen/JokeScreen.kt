package com.berkeerkec.foodrecipescompose.presentation.views.joke_Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(){

    Scaffold(topBar = { JokeAppBar() }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.LightGray)
        ) {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeAppBar(){
    TopAppBar(title = {
        Text(
            text = "Joke",
            color = Color.White
        )
    },
        colors = TopAppBarDefaults.largeTopAppBarColors(Purple40)
    )
}



@Preview
@Composable
fun FavScreenPreview(){
    JokeScreen()
}