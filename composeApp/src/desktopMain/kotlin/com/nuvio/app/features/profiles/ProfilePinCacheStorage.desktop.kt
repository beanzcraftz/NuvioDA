package com.nuvio.app.features.profiles

import java.util.prefs.Preferences




internal actual object ProfilePinCacheStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadPayload(profileIndex: Int): String? = preferences.get("payload_$profileIndex", null)
    actual fun savePayload(profileIndex: Int, payload: String) { preferences.put("payload_$profileIndex", payload) }
    actual fun removePayload(profileIndex: Int) { preferences.put("removePayload", profileIndex.toString()) }
}
