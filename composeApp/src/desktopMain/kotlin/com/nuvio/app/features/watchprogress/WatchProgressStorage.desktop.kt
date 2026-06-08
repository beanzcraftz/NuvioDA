package com.nuvio.app.features.watchprogress




internal actual object WatchProgressStorage {
    actual fun loadPayload(profileId: Int): String? = null
    actual fun savePayload(profileId: Int, payload: String) { }
}

