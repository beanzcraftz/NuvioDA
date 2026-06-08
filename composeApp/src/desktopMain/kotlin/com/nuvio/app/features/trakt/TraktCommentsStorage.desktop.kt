package com.nuvio.app.features.trakt

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object TraktCommentsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadEnabled(): Boolean? = preferences.get("loadEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveEnabled(enabled: Boolean) { preferences.put("saveEnabled", enabled.toString()) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

