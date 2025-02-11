package com.vaniala.movies.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("LongMethod")
@Composable
fun MovieDetailsSkeleton() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 0.8f),
            Color.LightGray.copy(alpha = 0.5f),
        ),
        start = Offset(x = offset * 100, y = 0f),
        end = Offset(x = offset * 300, y = 0f),
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(brush),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(24.dp)
                        .background(brush, RoundedCornerShape(4.dp)),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(16.dp)
                        .background(brush, RoundedCornerShape(4.dp)),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(16.dp)
                        .background(brush, RoundedCornerShape(4.dp)),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Box(
                modifier = Modifier
                    .size(43.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
            Box(
                modifier = Modifier
                    .size(43.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
        }
    }
}
