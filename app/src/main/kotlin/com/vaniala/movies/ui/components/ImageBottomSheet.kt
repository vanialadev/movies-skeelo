package com.vaniala.movies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaniala.movies.R
import com.vaniala.movies.utils.shareImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageBottomSheet(sheetState: SheetState? = null, onDismiss: () -> Unit = {}, url: String) {
    val context = LocalContext.current
    sheetState?.let {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = it,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
                    .padding(16.dp),
            ) {
                Text(
                    text = stringResource(R.string.image_options),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                TextButton(
                    onClick = {
                        context.shareImage(url)
                        onDismiss()
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp),
                    )
                    Text(text = stringResource(R.string.share))
                }
            }
        }
    }
}

