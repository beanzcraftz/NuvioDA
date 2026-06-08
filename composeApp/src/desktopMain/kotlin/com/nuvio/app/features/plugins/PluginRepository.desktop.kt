package com.nuvio.app.features.plugins

import java.util.prefs.Preferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual object PluginRepository {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    @Suppress("UNCHECKED_CAST")
    actual val uiState: StateFlow<PluginsUiState> = MutableStateFlow<Any?>(null) as StateFlow<PluginsUiState>

    actual fun initialize() { preferences.remove("initialize") }

    actual fun onProfileChanged(profileId: Int) { preferences.put("onProfileChanged", profileId.toString()) }

    actual fun clearLocalState() { preferences.remove("clearLocalState") }

    actual suspend fun pullFromServer(profileId: Int) {}

    actual suspend fun addRepository(rawUrl: String): AddPluginRepositoryResult = TODO()

    actual fun removeRepository(manifestUrl: String) { preferences.put("removeRepository", manifestUrl) }

    actual fun refreshAll() { preferences.remove("refreshAll") }

    actual fun refreshRepository(manifestUrl: String, pushAfterRefresh: Boolean) {}

    actual fun toggleScraper(scraperId: String, enabled: Boolean) {}

    actual fun setPluginsEnabled(enabled: Boolean) { preferences.put("setPluginsEnabled", enabled.toString()) }

    actual fun setGroupStreamsByRepository(enabled: Boolean) { preferences.put("setGroupStreamsByRepository", enabled.toString()) }

    actual fun getEnabledScrapersForType(type: String): List<PluginScraper> = emptyList()

    actual suspend fun testScraper(scraperId: String): Result<List<PluginRuntimeResult>> = Result.success(emptyList())

    actual suspend fun executeScraper(
        scraper: PluginScraper,
        tmdbId: String,
        mediaType: String,
        season: Int?,
        episode: Int?,
    ): Result<List<PluginRuntimeResult>> = Result.success(emptyList())
}
