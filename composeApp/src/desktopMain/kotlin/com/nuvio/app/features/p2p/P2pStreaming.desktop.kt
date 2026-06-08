package com.nuvio.app.features.p2p

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal actual object P2pSettingsStorage {
    actual fun loadP2pEnabled(): Boolean? = null
    actual fun saveP2pEnabled(enabled: Boolean) {}
    actual fun loadEnableUpload(): Boolean? = null
    actual fun saveEnableUpload(enabled: Boolean) {}
    actual fun loadHideTorrentStats(): Boolean? = null
    actual fun saveHideTorrentStats(enabled: Boolean) {}
}

actual object P2pStreamingEngine {
    @Suppress("UNCHECKED_CAST")
    actual val state: StateFlow<P2pStreamingState> = MutableStateFlow<Any?>(null) as StateFlow<P2pStreamingState>
    actual fun warmup() {}
    actual fun cooldownWarmup() {}
    actual suspend fun startStream(request: P2pStreamRequest): String = ""
    actual fun stopStream() {}
    actual fun shutdown() {}
}
