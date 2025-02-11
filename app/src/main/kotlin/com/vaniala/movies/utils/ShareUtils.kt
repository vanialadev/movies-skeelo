package com.vaniala.movies.utils

import android.content.Context
import android.content.Intent
import com.vaniala.movies.R

fun Context.shareImage(url: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }

    startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
}
