package com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.berkeerkec.foodrecipescompose.R

@Composable
fun RecipeRow(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
        ) {

        Box {
            Row(
                modifier = Modifier
                .fillMaxWidth()
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.kabak_jpg),
                    contentDescription = "food",
                    modifier = Modifier
                        .weight(1f),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                    .weight(1f)
                ) {

                    Text(
                        text = "Kabak Dolması",
                        modifier = Modifier
                            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Hafifliği ve lezzeti ile tartışmasız çok iyi olan kabak dolması yapımının kolaylığı ile de beğeni toplayacak. Yaz kış demeden tüketebileceğimiz, yanında yoğurtla mükemmel bir",
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
                            .padding(bottom = 24.dp),
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
                                text = "112",
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
                                text = "15",
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

                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_leaf),
                                contentDescription = "vegan"
                            )

                            Text(
                                text = "vegan",
                                color = Color(R.color.lighBlack),
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
    RecipeRow()
}