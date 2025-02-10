package com.vaniala.movies.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vaniala.movies.R
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_SMALL

@Composable
fun AsyncImagePoster(url: String?, modifier: Modifier = Modifier) {
    Box {
        // todo:v fazer umaa funcao pra Imagerequest
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("$IMAGE_URL_SMALL$url")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .build(),
            contentDescription = stringResource(R.string.content_description_image),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.error),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .clip(RoundedCornerShape(8.dp)),
        )
    }
}

@Preview
@Composable
private fun AsyncImagePosterPreview() {
    AsyncImagePoster(url = String())
}
