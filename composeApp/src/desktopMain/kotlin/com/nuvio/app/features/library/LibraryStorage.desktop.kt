package com.nuvio.app.features.library

import java.util.prefs.Preferences




internal actual object LibraryStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadPayload(profileId: Int): String? = preferences.get("payload_$profileId", null)
    actual fun savePayload(profileId: Int, payload: String) { preferences.put("payload_$profileId", payload) }
}

