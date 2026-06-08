package com.nuvio.app.features.mdblist

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object MdbListSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadEnabled(): Boolean? = preferences.get("loadEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveEnabled(enabled: Boolean) { preferences.put("saveEnabled", enabled.toString()) }
    actual fun loadApiKey(): String? = preferences.get("loadApiKey", null)
    actual fun saveApiKey(apiKey: String) { preferences.put("saveApiKey", apiKey) }
    actual fun loadUseImdb(): Boolean? = preferences.get("loadUseImdb", null)?.toBooleanStrictOrNull()
    actual fun saveUseImdb(enabled: Boolean) { preferences.put("saveUseImdb", enabled.toString()) }
    actual fun loadUseTmdb(): Boolean? = preferences.get("loadUseTmdb", null)?.toBooleanStrictOrNull()
    actual fun saveUseTmdb(enabled: Boolean) { preferences.put("saveUseTmdb", enabled.toString()) }
    actual fun loadUseTomatoes(): Boolean? = preferences.get("loadUseTomatoes", null)?.toBooleanStrictOrNull()
    actual fun saveUseTomatoes(enabled: Boolean) { preferences.put("saveUseTomatoes", enabled.toString()) }
    actual fun loadUseMetacritic(): Boolean? = preferences.get("loadUseMetacritic", null)?.toBooleanStrictOrNull()
    actual fun saveUseMetacritic(enabled: Boolean) { preferences.put("saveUseMetacritic", enabled.toString()) }
    actual fun loadUseTrakt(): Boolean? = preferences.get("loadUseTrakt", null)?.toBooleanStrictOrNull()
    actual fun saveUseTrakt(enabled: Boolean) { preferences.put("saveUseTrakt", enabled.toString()) }
    actual fun loadUseLetterboxd(): Boolean? = preferences.get("loadUseLetterboxd", null)?.toBooleanStrictOrNull()
    actual fun saveUseLetterboxd(enabled: Boolean) { preferences.put("saveUseLetterboxd", enabled.toString()) }
    actual fun loadUseAudience(): Boolean? = preferences.get("loadUseAudience", null)?.toBooleanStrictOrNull()
    actual fun saveUseAudience(enabled: Boolean) { preferences.put("saveUseAudience", enabled.toString()) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

