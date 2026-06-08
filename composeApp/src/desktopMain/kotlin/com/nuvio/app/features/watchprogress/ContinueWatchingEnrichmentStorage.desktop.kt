package com.nuvio.app.features.watchprogress

import java.util.prefs.Preferences




internal actual object ContinueWatchingEnrichmentStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadPayload(key: String): String? = preferences.get("payload_$key", null)
    actual fun savePayload(key: String, payload: String) { preferences.put("payload_$key", payload) }
    actual fun removePayload(key: String) { preferences.remove("payload_$key") }
}

