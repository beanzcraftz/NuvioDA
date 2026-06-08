package com.nuvio.app.features.streams

import kotlinx.serialization.json.JsonObject



internal actual object StreamBadgeSettingsStorage {
    actual fun loadStreamBadgeRules(): String? = null
    actual fun saveStreamBadgeRules(rules: String) { }
    actual fun loadShowFileSizeBadges(): Boolean? = null
    actual fun saveShowFileSizeBadges(enabled: Boolean) { }
    actual fun loadStreamBadgePlacement(): String? = null
    actual fun saveStreamBadgePlacement(placement: String) { }
    actual fun loadLegacyDebridStreamBadgeRules(): String? = null
    actual fun clearLegacyDebridStreamBadgeRules() { }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

