package com.nuvio.app.features.p2p

import java.util.prefs.Preferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal actual object P2pSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadP2pEnabled(): Boolean? = preferences.get("loadP2pEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveP2pEnabled(enabled: Boolean) { preferences.put("saveP2pEnabled", enabled.toString()) }
    actual fun loadEnableUpload(): Boolean? = preferences.get("loadEnableUpload", null)?.toBooleanStrictOrNull()
    actual fun saveEnableUpload(enabled: Boolean) { preferences.put("saveEnableUpload", enabled.toString()) }
    actual fun loadHideTorrentStats(): Boolean? = preferences.get("loadHideTorrentStats", null)?.toBooleanStrictOrNull()
    actual fun saveHideTorrentStats(enabled: Boolean) { preferences.put("saveHideTorrentStats", enabled.toString()) }
}

actual object P2pStreamingEngine {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    @Suppress("UNCHECKED_CAST")
    actual val state: StateFlow<P2pStreamingState> = MutableStateFlow<Any?>(null) as StateFlow<P2pStreamingState>
    actual fun warmup() { preferences.remove("warmup") }
    actual fun cooldownWarmup() { preferences.remove("cooldownWarmup") }
    actual suspend fun startStream(request: P2pStreamRequest): String = ""
    actual fun stopStream() { preferences.remove("stopStream") }
    actual fun shutdown() { preferences.remove("shutdown") }
}
