package com.nuvio.app.features.downloads

import java.util.prefs.Preferences

internal actual object DownloadsPlatformDownloader {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun start(
        request: DownloadPlatformRequest,
        onProgress: (downloadedBytes: Long, totalBytes: Long?) -> Unit,
        onSuccess: (localFileUri: String, totalBytes: Long?) -> Unit,
        onFailure: (message: String) -> Unit,
    ): DownloadsTaskHandle = object : DownloadsTaskHandle {
        override fun cancel() {}
    }

    actual fun removeFile(localFileUri: String?): Boolean = false

    actual fun removePartialFile(destinationFileName: String): Boolean = false

    actual fun resolveLocalFileUri(localFileUri: String?, destinationFileName: String): String? = null
}
