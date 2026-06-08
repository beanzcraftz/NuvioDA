package com.nuvio.app.features.streams

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object StreamBadgeSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadStreamBadgeRules(): String? = preferences.get("loadStreamBadgeRules", null)
    actual fun saveStreamBadgeRules(rules: String) { preferences.put("saveStreamBadgeRules", rules) }
    actual fun loadShowFileSizeBadges(): Boolean? = preferences.get("loadShowFileSizeBadges", null)?.toBooleanStrictOrNull()
    actual fun saveShowFileSizeBadges(enabled: Boolean) { preferences.put("saveShowFileSizeBadges", enabled.toString()) }
    actual fun loadStreamBadgePlacement(): String? = preferences.get("loadStreamBadgePlacement", null)
    actual fun saveStreamBadgePlacement(placement: String) { preferences.put("saveStreamBadgePlacement", placement) }
    actual fun loadLegacyDebridStreamBadgeRules(): String? = preferences.get("loadLegacyDebridStreamBadgeRules", null)
    actual fun clearLegacyDebridStreamBadgeRules() { preferences.remove("clearLegacyDebridStreamBadgeRules") }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

