package com.nuvio.app.features.addons

internal actual object AddonStorage {
    actual fun loadInstalledAddonUrls(profileId: Int): List<String> = emptyList()
    actual fun saveInstalledAddonUrls(profileId: Int, urls: List<String>) {}
    actual fun loadAddonEnabledStates(profileId: Int): Map<String, Boolean> = emptyMap()
    actual fun saveAddonEnabledStates(profileId: Int, states: Map<String, Boolean>) {}
}

actual suspend fun httpGetText(url: String): String = ""

actual suspend fun httpPostJson(url: String, body: String): String = ""

actual suspend fun httpGetTextWithHeaders(
    url: String,
    headers: Map<String, String>,
): String = ""

actual suspend fun httpPostJsonWithHeaders(
    url: String,
    body: String,
    headers: Map<String, String>,
): String = ""

actual suspend fun httpRequestRaw(
    method: String,
    url: String,
    headers: Map<String, String>,
    body: String,
    followRedirects: Boolean,
): RawHttpResponse = RawHttpResponse(0, "", url, "", emptyMap())
