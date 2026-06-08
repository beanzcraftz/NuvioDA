package com.nuvio.app.features.collection

import java.util.prefs.Preferences




internal actual object CollectionMobileSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadPayload(): String? = preferences.get("payload", null)
    actual fun savePayload(payload: String) { preferences.put("payload", payload) }
}

