package com.nuvio.app.features.profiles

import java.util.prefs.Preferences
import java.security.MessageDigest

internal actual object ProfilePinCrypto {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun sha256Hex(value: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(value.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
