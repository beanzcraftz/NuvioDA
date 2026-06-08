package com.nuvio.app.features.watched




actual object WatchedStorage {
    actual fun loadPayload(profileId: Int): String? = null
    actual fun savePayload(profileId: Int, payload: String) { }
}


