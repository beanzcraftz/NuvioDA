package com.nuvio.app.features.library




internal actual object LibraryStorage {
    actual fun loadPayload(profileId: Int): String? = null
    actual fun savePayload(profileId: Int, payload: String) { }
}

