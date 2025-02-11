package com.vaniala.movies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vaniala.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageBottomSheet(
    sheetState: SheetState? = null,
    onDismiss: () -> Unit = {},
    onShare: () -> Unit = {},
    onDownload: () -> Unit = {},
) {
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
                TextButton(
                    onClick = {
                        onShare
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
                TextButton(
                    onClick = {
                        onDownload()
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp),
                    )
                    Text(text = stringResource(R.string.download))
                }
            }
        }
    }
}
