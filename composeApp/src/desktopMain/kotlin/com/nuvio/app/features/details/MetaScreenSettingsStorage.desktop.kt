package com.nuvio.app.features.details

import java.util.prefs.Preferences




internal actual object MetaScreenSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadPayload(): String? = preferences.get("payload", null)
    actual fun savePayload(payload: String) { preferences.put("payload", payload) }
}
