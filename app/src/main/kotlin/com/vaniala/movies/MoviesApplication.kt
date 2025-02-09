package com.vaniala.movies

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.vaniala.movies.data.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

private const val FIFTEEN_PERCENT = 0.15
private const val FIVE_PERCENT = 0.05

@HiltAndroidApp
class MoviesApplication :
    Application(),
    ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(TagAkuAku())
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .components {
            add(SvgDecoder.Factory())
        }
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(FIFTEEN_PERCENT)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .directory(this.cacheDir.resolve("image_cache"))
                .maxSizePercent(FIVE_PERCENT)
                .build()
        }
        .crossfade(true)
        .logger(DebugLogger())
        .build()
}

class TagAkuAku : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String = "akuaku"
}
