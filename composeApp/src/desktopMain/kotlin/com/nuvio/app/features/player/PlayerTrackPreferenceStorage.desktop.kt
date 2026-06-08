package com.nuvio.app.features.player

import java.util.prefs.Preferences

internal actual object PlayerTrackPreferenceStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun load(contentId: String): PersistedPlayerTrackPreference? = null
    actual fun save(contentId: String, preference: PersistedPlayerTrackPreference) {}
    actual fun loadSubtitleDelayMs(videoId: String): Int? = null
    actual fun saveSubtitleDelayMs(videoId: String, delayMs: Int) {}
}
