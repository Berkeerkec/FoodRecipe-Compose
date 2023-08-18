package com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkeerkec.foodrecipescompose.R

@Composable
fun NoInternetScreen(){

    Box(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_sad),
                contentDescription = "No Internet",
                modifier = Modifier
                    .alpha(0.5f)
                    .size(100.dp, 100.dp)
            )
            Text(
                text = "No Internet Connection",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(0.5f)
            )
        }
    }
}

@Preview
@Composable
fun NoInternetPreview(){
    NoInternetScreen()
}