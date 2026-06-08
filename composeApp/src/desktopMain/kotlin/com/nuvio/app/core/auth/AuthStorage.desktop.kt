package com.nuvio.app.core.auth




internal actual object AuthStorage {
    actual fun loadAnonymousUserId(): String? = null
    actual fun saveAnonymousUserId(userId: String) { }
    actual fun clearAnonymousUserId() { }
}

