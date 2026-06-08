package com.nuvio.app.features.streams

import java.util.prefs.Preferences




internal actual object StreamLinkCacheStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadEntry(hashedKey: String): String? = preferences.get("stream_$hashedKey", null)
    actual fun saveEntry(hashedKey: String, payload: String) { preferences.put("stream_$hashedKey", payload) }
    actual fun removeEntry(hashedKey: String) { preferences.put("removeEntry", hashedKey) }
}

