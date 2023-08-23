package com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkeerkec.foodrecipescompose.R
import com.berkeerkec.foodrecipescompose.presentation.ui.theme.Purple40
import com.berkeerkec.foodrecipescompose.presentation.view_models.main_view_model.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    viewModel: MainViewModel = hiltViewModel()
){

    val state = viewModel.state.value

    Scaffold(topBar = { RecipesAppBar()},
        modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {

                    state.recipes.let {food ->
                        LazyColumn(modifier = Modifier.fillMaxSize()){
                            if (food != null) {
                                items(food.results){ result ->
                                    RecipeRow(recipes = result)
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    FloatingAction()

                }
            }
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesAppBar(){
    TopAppBar(title = {
        Text(
            text = "Recipes",
            color = Color.White
        )
    },
        colors = TopAppBarDefaults.largeTopAppBarColors(Purple40)
    )
}

@Composable
fun FloatingAction(){
    FloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .padding(bottom = 16.dp, end = 16.dp),
        shape = CircleShape,
        containerColor = Color.White
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_food),
            contentDescription = "Filter"
        )
    }
}

@Preview
@Composable
fun RecipesScreenPreview(){
    RecipeScreen()
}
