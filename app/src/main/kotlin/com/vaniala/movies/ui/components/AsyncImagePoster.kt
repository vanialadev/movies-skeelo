package com.vaniala.movies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vaniala.movies.R
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_SMALL

@Composable
fun AsyncImagePoster(url: String?, voteAverageString: Double? = null, modifier: Modifier = Modifier) {
    Box {
        // todo:v fazer umaa funcao pra Imagerequest
        voteAverageString?.let {
            Box(
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                    )
                    .align(Alignment.TopEnd),
            ) {
                Text(
                    it.toString(),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }
        }
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
                .clip(RoundedCornerShape(8.dp))
                .zIndex(-1f),
        )
    }
}

@Preview
@Composable
private fun AsyncImagePosterPreview() {
    AsyncImagePoster(url = String(), 6.7)
}
