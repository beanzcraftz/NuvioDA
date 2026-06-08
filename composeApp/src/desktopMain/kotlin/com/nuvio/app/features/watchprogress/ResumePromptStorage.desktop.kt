package com.nuvio.app.features.watchprogress

import java.util.prefs.Preferences




internal actual object ResumePromptStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadWasInPlayer(): Boolean = preferences.getBoolean("loadWasInPlayer", false)
    actual fun saveWasInPlayer(value: Boolean) { preferences.put("saveWasInPlayer", value.toString()) }
    actual fun loadLastPlayerVideoId(): String? = preferences.get("loadLastPlayerVideoId", null)
    actual fun saveLastPlayerVideoId(videoId: String?) { if (videoId != null) preferences.put("lastVideo", videoId) else preferences.remove("lastVideo") }
}

