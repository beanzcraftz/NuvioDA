package com.nuvio.app.features.details

import java.util.prefs.Preferences

internal actual object SeasonViewModeStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun load(): SeasonViewMode? = null
    actual fun save(mode: SeasonViewMode) {}
}
