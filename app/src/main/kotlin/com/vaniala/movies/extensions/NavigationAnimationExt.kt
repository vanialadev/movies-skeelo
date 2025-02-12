package com.vaniala.movies.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

private const val ANIMATION_DURATION = 300

enum class ScaleTransitionDirection {
    INWARDS,
    OUTWARDS,
}

private const val SCALE_80_PERCENT = 0.8f
private const val SCALE_120_PERCENT = 0.8f

fun scaleIntoContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
    initialScale: Float = if (direction == ScaleTransitionDirection.OUTWARDS) SCALE_80_PERCENT else SCALE_120_PERCENT,
): EnterTransition = scaleIn(
    animationSpec = tween(ANIMATION_DURATION),
    initialScale = initialScale,
) + fadeIn(animationSpec = tween(ANIMATION_DURATION))

fun scaleOutOfContainer(
    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
    targetScale: Float = if (direction == ScaleTransitionDirection.INWARDS) SCALE_80_PERCENT else SCALE_120_PERCENT,
): ExitTransition = scaleOut(
    animationSpec = tween(ANIMATION_DURATION),
    targetScale = targetScale,
) + fadeOut(animationSpec = tween(ANIMATION_DURATION))

fun slideVerticallyIntoContainer(direction: AnimatedContentTransitionScope.SlideDirection): EnterTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Up -> slideInVertically(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetY = { it },
    )

    AnimatedContentTransitionScope.SlideDirection.Down -> slideInVertically(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetY = { -it },
    )

    else -> slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { it },
    )
} + fadeIn(animationSpec = tween(ANIMATION_DURATION))

fun slideVerticallyOutOfContainer(direction: AnimatedContentTransitionScope.SlideDirection): ExitTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Up -> slideOutVertically(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetY = { -it },
    )

    AnimatedContentTransitionScope.SlideDirection.Down -> slideOutVertically(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetY = { it },
    )

    else -> slideOutHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetX = { it },
    )
} + fadeOut(animationSpec = tween(ANIMATION_DURATION))

fun slideHorizontallyIntoContainer(direction: AnimatedContentTransitionScope.SlideDirection): EnterTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Left -> slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { it },
    )

    AnimatedContentTransitionScope.SlideDirection.Right -> slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { -it },
    )

    else -> slideInVertically(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetY = { it },
    )
} + fadeIn(animationSpec = tween(ANIMATION_DURATION))

fun slideHorizontallyOutOfContainer(direction: AnimatedContentTransitionScope.SlideDirection): ExitTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Left -> slideOutHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetX = { -it },
    )

    AnimatedContentTransitionScope.SlideDirection.Right -> slideOutHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetX = { it },
    )

    else -> slideOutVertically(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetY = { it },
    )
} + fadeOut(animationSpec = tween(ANIMATION_DURATION))
