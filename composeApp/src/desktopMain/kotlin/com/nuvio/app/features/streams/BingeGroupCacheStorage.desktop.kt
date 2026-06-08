package com.nuvio.app.features.streams




internal actual object BingeGroupCacheStorage {
    actual fun load(hashedKey: String): String? = null
    actual fun save(hashedKey: String, value: String) { }
    actual fun remove(hashedKey: String) { }
}

