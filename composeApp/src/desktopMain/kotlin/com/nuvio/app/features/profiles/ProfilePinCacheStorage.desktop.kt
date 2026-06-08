package com.nuvio.app.features.profiles




internal actual object ProfilePinCacheStorage {
    actual fun loadPayload(profileIndex: Int): String? = null
    actual fun savePayload(profileIndex: Int, payload: String) { }
    actual fun removePayload(profileIndex: Int) { }
}
