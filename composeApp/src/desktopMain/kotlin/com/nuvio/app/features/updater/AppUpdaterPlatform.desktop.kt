package com.nuvio.app.features.updater

import java.util.prefs.Preferences
import kotlinx.coroutines.runBlocking
import nuvio.composeapp.generated.resources.Res
import nuvio.composeapp.generated.resources.updates_not_available
import org.jetbrains.compose.resources.getString

actual object AppUpdaterPlatform {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual val isSupported: Boolean = false

    actual fun getSupportedAbis(): List<String> = emptyList()

    actual fun getIgnoredTag(): String? = preferences.get("getIgnoredTag", null)

    actual fun setIgnoredTag(tag: String?) = Unit

    actual suspend fun downloadApk(
        assetUrl: String,
        assetName: String,
        onProgress: (downloadedBytes: Long, totalBytes: Long?) -> Unit,
    ): Result<String> = Result.failure(IllegalStateException(getString(Res.string.updates_not_available)))

    actual fun canRequestPackageInstalls(): Boolean = preferences.getBoolean("canRequestPackageInstalls", false)

    actual fun openUnknownSourcesSettings() = Unit

    actual fun installDownloadedApk(path: String): Result<Unit> =
        Result.failure(IllegalStateException(runBlocking { getString(Res.string.updates_not_available) }))
}