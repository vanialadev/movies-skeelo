package com.vaniala.movies.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Suppress("MagicNumber")
@Composable
fun AnimatedIcon(isActive: Boolean, activeIcon: ImageVector, inactiveIcon: ImageVector, modifier: Modifier = Modifier) {
    AnimatedContent(
        targetState = isActive,
        transitionSpec = {
            scaleIn(animationSpec = tween(300)) togetherWith
                scaleOut(animationSpec = tween(300))
        },
    ) { isActiveState ->
        val scale = animateFloatAsState(
            targetValue = if (isActiveState) 1.2f else 1f,
            animationSpec = tween(300),
        )
        Icon(
            imageVector = if (isActiveState) activeIcon else inactiveIcon,
            contentDescription = null,
            modifier = modifier
                .size(28.dp)
                .scale(scale.value),
            tint = if (isActiveState) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            },
        )
    }
}
