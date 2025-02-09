package com.vaniala.movies.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.profile.Avatar
import com.vaniala.movies.ui.utils.Constants.GRAVATAR_URL

@Composable
fun AsyncImageProfile(avatar: Avatar?, modifier: Modifier = Modifier) {
    val urlNew = if (avatar?.gravatar?.hash != null) {
        GRAVATAR_URL + avatar.gravatar?.hash
    } else {
        avatar?.tmdb?.avatarPath
    }
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("$urlNew")
                .placeholder(R.drawable.user_placeholder)
//                .error(R.drawable.error)
                .build(),
            contentDescription = stringResource(R.string.content_description_image),
            placeholder = ColorPainter(Color.LightGray),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(240.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
        )
    }
}

@Preview
@Composable
private fun AsyncImageProfilePreview() {
    AsyncImageProfile(null)
}
