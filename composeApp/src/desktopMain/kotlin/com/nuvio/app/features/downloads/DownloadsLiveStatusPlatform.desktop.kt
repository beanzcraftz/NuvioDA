package com.nuvio.app.features.downloads

import java.util.prefs.Preferences




internal actual object DownloadsLiveStatusPlatform {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun onItemsChanged(items: List<DownloadItem>) { }
}

