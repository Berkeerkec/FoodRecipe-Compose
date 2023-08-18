package com.berkeerkec.foodrecipescompose.presentation.views.recipe_screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer(){
    val shimmerColor = listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
    
    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 3.dp)
    ) {

        Spacer(
            modifier = Modifier
                .height(200.dp)
                .clip(RectangleShape)
                .background(brush)
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 12.dp)
                    .background(brush)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 12.dp)
                    .background(brush)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(
                    modifier = Modifier
                        .size(40.dp,45.dp)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Spacer(
                    modifier = Modifier
                        .size(40.dp,45.dp)
                        .background(brush)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Spacer(
                    modifier = Modifier
                        .size(40.dp,45.dp)
                        .background(brush)
                )
            }
        }

    }
    
}

@Composable
@Preview(showBackground = true)
fun ShimmerGridPreview(){
    ShimmerGridItem(brush = Brush.linearGradient(
        listOf(
            Color.LightGray.copy(0.6f),
            Color.LightGray.copy(0.2f),
            Color.LightGray.copy(0.6f)
        )
    ))
}