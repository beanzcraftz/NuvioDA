package com.nuvio.app.features.debrid

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object DebridSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadEnabled(): Boolean? = preferences.get("loadEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveEnabled(enabled: Boolean) { preferences.put("saveEnabled", enabled.toString()) }
    actual fun loadCloudLibraryEnabled(): Boolean? = preferences.get("loadCloudLibraryEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveCloudLibraryEnabled(enabled: Boolean) { preferences.put("saveCloudLibraryEnabled", enabled.toString()) }
    actual fun loadPreferredResolverProviderId(): String? = preferences.get("loadPreferredResolverProviderId", null)
    actual fun savePreferredResolverProviderId(providerId: String) { preferences.put("savePreferredResolverProviderId", providerId) }
    actual fun loadProviderApiKey(providerId: String): String? = null
    actual fun saveProviderApiKey(providerId: String, apiKey: String) { }
    actual fun loadTorboxApiKey(): String? = preferences.get("loadTorboxApiKey", null)
    actual fun saveTorboxApiKey(apiKey: String) { preferences.put("saveTorboxApiKey", apiKey) }
    actual fun loadRealDebridApiKey(): String? = preferences.get("loadRealDebridApiKey", null)
    actual fun saveRealDebridApiKey(apiKey: String) { preferences.put("saveRealDebridApiKey", apiKey) }
    actual fun loadInstantPlaybackPreparationLimit(): Int? = preferences.get("loadInstantPlaybackPreparationLimit", null)?.toIntOrNull()
    actual fun saveInstantPlaybackPreparationLimit(limit: Int) { preferences.put("saveInstantPlaybackPreparationLimit", limit.toString()) }
    actual fun loadStreamMaxResults(): Int? = preferences.get("loadStreamMaxResults", null)?.toIntOrNull()
    actual fun saveStreamMaxResults(maxResults: Int) { preferences.put("saveStreamMaxResults", maxResults.toString()) }
    actual fun loadStreamSortMode(): String? = preferences.get("loadStreamSortMode", null)
    actual fun saveStreamSortMode(mode: String) { preferences.put("saveStreamSortMode", mode) }
    actual fun loadStreamMinimumQuality(): String? = preferences.get("loadStreamMinimumQuality", null)
    actual fun saveStreamMinimumQuality(quality: String) { preferences.put("saveStreamMinimumQuality", quality) }
    actual fun loadStreamDolbyVisionFilter(): String? = preferences.get("loadStreamDolbyVisionFilter", null)
    actual fun saveStreamDolbyVisionFilter(filter: String) { preferences.put("saveStreamDolbyVisionFilter", filter) }
    actual fun loadStreamHdrFilter(): String? = preferences.get("loadStreamHdrFilter", null)
    actual fun saveStreamHdrFilter(filter: String) { preferences.put("saveStreamHdrFilter", filter) }
    actual fun loadStreamCodecFilter(): String? = preferences.get("loadStreamCodecFilter", null)
    actual fun saveStreamCodecFilter(filter: String) { preferences.put("saveStreamCodecFilter", filter) }
    actual fun loadStreamPreferences(): String? = preferences.get("loadStreamPreferences", null)
    actual fun saveStreamPreferences(preferences: String) { DebridSettingsStorage.preferences.put("saveStreamPreferences", preferences) }
    actual fun loadStreamNameTemplate(): String? = preferences.get("loadStreamNameTemplate", null)
    actual fun saveStreamNameTemplate(template: String) { preferences.put("saveStreamNameTemplate", template) }
    actual fun loadStreamDescriptionTemplate(): String? = preferences.get("loadStreamDescriptionTemplate", null)
    actual fun saveStreamDescriptionTemplate(template: String) { preferences.put("saveStreamDescriptionTemplate", template) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

