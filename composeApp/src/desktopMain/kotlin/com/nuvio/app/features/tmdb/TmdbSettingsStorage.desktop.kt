package com.nuvio.app.features.tmdb

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object TmdbSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadEnabled(): Boolean? = preferences.get("loadEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveEnabled(enabled: Boolean) { preferences.put("saveEnabled", enabled.toString()) }
    actual fun loadApiKey(): String? = preferences.get("loadApiKey", null)
    actual fun saveApiKey(apiKey: String) { preferences.put("saveApiKey", apiKey) }
    actual fun loadLanguage(): String? = preferences.get("loadLanguage", null)
    actual fun saveLanguage(language: String) { preferences.put("saveLanguage", language) }
    actual fun loadUseTrailers(): Boolean? = preferences.get("loadUseTrailers", null)?.toBooleanStrictOrNull()
    actual fun saveUseTrailers(enabled: Boolean) { preferences.put("saveUseTrailers", enabled.toString()) }
    actual fun loadUseArtwork(): Boolean? = preferences.get("loadUseArtwork", null)?.toBooleanStrictOrNull()
    actual fun saveUseArtwork(enabled: Boolean) { preferences.put("saveUseArtwork", enabled.toString()) }
    actual fun loadUseBasicInfo(): Boolean? = preferences.get("loadUseBasicInfo", null)?.toBooleanStrictOrNull()
    actual fun saveUseBasicInfo(enabled: Boolean) { preferences.put("saveUseBasicInfo", enabled.toString()) }
    actual fun loadUseDetails(): Boolean? = preferences.get("loadUseDetails", null)?.toBooleanStrictOrNull()
    actual fun saveUseDetails(enabled: Boolean) { preferences.put("saveUseDetails", enabled.toString()) }
    actual fun loadUseCredits(): Boolean? = preferences.get("loadUseCredits", null)?.toBooleanStrictOrNull()
    actual fun saveUseCredits(enabled: Boolean) { preferences.put("saveUseCredits", enabled.toString()) }
    actual fun loadUseProductions(): Boolean? = preferences.get("loadUseProductions", null)?.toBooleanStrictOrNull()
    actual fun saveUseProductions(enabled: Boolean) { preferences.put("saveUseProductions", enabled.toString()) }
    actual fun loadUseNetworks(): Boolean? = preferences.get("loadUseNetworks", null)?.toBooleanStrictOrNull()
    actual fun saveUseNetworks(enabled: Boolean) { preferences.put("saveUseNetworks", enabled.toString()) }
    actual fun loadUseEpisodes(): Boolean? = preferences.get("loadUseEpisodes", null)?.toBooleanStrictOrNull()
    actual fun saveUseEpisodes(enabled: Boolean) { preferences.put("saveUseEpisodes", enabled.toString()) }
    actual fun loadUseSeasonPosters(): Boolean? = preferences.get("loadUseSeasonPosters", null)?.toBooleanStrictOrNull()
    actual fun saveUseSeasonPosters(enabled: Boolean) { preferences.put("saveUseSeasonPosters", enabled.toString()) }
    actual fun loadUseMoreLikeThis(): Boolean? = preferences.get("loadUseMoreLikeThis", null)?.toBooleanStrictOrNull()
    actual fun saveUseMoreLikeThis(enabled: Boolean) { preferences.put("saveUseMoreLikeThis", enabled.toString()) }
    actual fun loadUseCollections(): Boolean? = preferences.get("loadUseCollections", null)?.toBooleanStrictOrNull()
    actual fun saveUseCollections(enabled: Boolean) { preferences.put("saveUseCollections", enabled.toString()) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

