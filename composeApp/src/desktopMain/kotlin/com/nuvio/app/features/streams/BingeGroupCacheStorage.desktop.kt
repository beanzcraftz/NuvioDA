package com.nuvio.app.features.streams

import java.util.prefs.Preferences




internal actual object BingeGroupCacheStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun load(hashedKey: String): String? = preferences.get("binge_$hashedKey", null)
    actual fun save(hashedKey: String, value: String) { preferences.put("binge_$hashedKey", value) }
    actual fun remove(hashedKey: String) { preferences.put("remove", hashedKey) }
}

