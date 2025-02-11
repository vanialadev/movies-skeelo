package com.vaniala.movies.utils

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.getSystemService
import com.vaniala.movies.R

fun Context.shareImage(url: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }

    startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
}

fun Context.downloadImage(imageUrl: String, fileName: String) {
    val request = DownloadManager.Request(Uri.parse(imageUrl))
        .setTitle(fileName)
        .setDescription(getString(R.string.downloading))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

    val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}
