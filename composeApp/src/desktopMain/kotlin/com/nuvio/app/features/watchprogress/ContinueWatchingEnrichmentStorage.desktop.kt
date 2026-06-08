package com.nuvio.app.features.watchprogress




internal actual object ContinueWatchingEnrichmentStorage {
    actual fun loadPayload(key: String): String? = null
    actual fun savePayload(key: String, payload: String) { }
    actual fun removePayload(key: String) { }
}

