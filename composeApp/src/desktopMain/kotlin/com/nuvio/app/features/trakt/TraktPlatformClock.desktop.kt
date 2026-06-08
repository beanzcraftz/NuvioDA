package com.nuvio.app.features.trakt




internal actual object TraktPlatformClock {
    actual fun nowEpochMs(): Long = 0
    actual fun parseIsoDateTimeToEpochMs(value: String): Long? = null
    actual fun availableProcessors(): Int = 0
}

