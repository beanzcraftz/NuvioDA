package com.nuvio.app.core.auth

import java.util.prefs.Preferences




internal actual object AuthStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadAnonymousUserId(): String? = preferences.get("loadAnonymousUserId", null)
    actual fun saveAnonymousUserId(userId: String) { preferences.put("saveAnonymousUserId", userId) }
    actual fun clearAnonymousUserId() { preferences.remove("clearAnonymousUserId") }
}

