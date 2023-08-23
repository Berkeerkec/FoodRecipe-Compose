package com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.berkeerkec.foodrecipescompose.R
import com.berkeerkec.foodrecipescompose.data.remote.dto.FoodRecipe
import com.berkeerkec.foodrecipescompose.data.remote.dto.Result

@Composable
fun RecipeRow(
    recipes : Result
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 2.dp)
            .height(230.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
        ) {

        Box {
            Row(
                modifier = Modifier
                .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .align(Alignment.CenterVertically)
                ) {
                    AsyncImage(
                        model = recipes.image,
                        contentDescription = recipes.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                    .weight(1f)
                ) {

                    Text(
                        text = recipes.title,
                        modifier = Modifier
                            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = recipes.summary,
                        modifier = Modifier
                            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                            .fillMaxWidth(),
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF484C52)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        ) {

                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_favorite),
                                contentDescription = "favori"
                            )

                            Text(
                                text = recipes.aggregateLikes.toString(),
                                color = Color(0xFFFF4646),
                                fontSize = 12.sp
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        ) {

                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_time),
                                contentDescription = "time"
                            )

                            Text(
                                text = recipes.readyInMinutes.toString(),
                                color = Color(0xFFFFC114),
                                fontSize = 12.sp
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        ) {

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_leaf),
                                contentDescription = "vegan",
                                tint = if (recipes.vegan) Color(0xFF4CAF50) else Color(0xFF484C52)
                            )

                            Text(
                                text = "vegan",
                                color = if (recipes.vegan) Color(0xFF4CAF50) else Color(0xFF484C52),
                                fontSize = 12.sp
                            )
                        }

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeRowPreview(){
}