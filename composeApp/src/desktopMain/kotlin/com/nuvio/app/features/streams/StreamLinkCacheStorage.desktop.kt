package com.nuvio.app.features.streams




internal actual object StreamLinkCacheStorage {
    actual fun loadEntry(hashedKey: String): String? = null
    actual fun saveEntry(hashedKey: String, payload: String) { }
    actual fun removeEntry(hashedKey: String) { }
}

