package com.nuvio.app.features.watchprogress




internal actual object ResumePromptStorage {
    actual fun loadWasInPlayer(): Boolean = false
    actual fun saveWasInPlayer(value: Boolean) { }
    actual fun loadLastPlayerVideoId(): String? = null
    actual fun saveLastPlayerVideoId(videoId: String?) { }
}

