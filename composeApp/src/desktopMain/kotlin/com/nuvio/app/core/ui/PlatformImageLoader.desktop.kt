package com.nuvio.app.core.ui

import coil3.ImageLoader

internal actual fun ImageLoader.Builder.configurePlatformImageLoader(): ImageLoader.Builder {
    // For Desktop, no special configuration is usually needed compared to Android
    return this
}
