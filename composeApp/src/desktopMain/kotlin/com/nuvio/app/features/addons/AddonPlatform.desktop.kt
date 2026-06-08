package com.nuvio.app.features.addons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.util.prefs.Preferences
import kotlin.text.Charsets

actual object AddonStorage {
    private val preferences: Preferences = Preferences.userRoot().node("nuvio_addons")
    private const val addonUrlsKey = "installed_manifest_urls"
    private const val addonEnabledStatesKey = "installed_manifest_enabled_states"

    actual fun loadInstalledAddonUrls(profileId: Int): List<String> =
        preferences.get("${addonUrlsKey}_$profileId", "")
            .lineSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toList()

    actual fun saveInstalledAddonUrls(profileId: Int, urls: List<String>) {
        preferences.put("${addonUrlsKey}_$profileId", urls.joinToString(separator = "\n"))
    }

    actual fun loadAddonEnabledStates(profileId: Int): Map<String, Boolean> =
        preferences.get("${addonEnabledStatesKey}_$profileId", "")
            .lineSequence()
            .mapNotNull(::parseEnabledStateLine)
            .toMap()

    actual fun saveAddonEnabledStates(profileId: Int, states: Map<String, Boolean>) {
        val payload = states.entries.joinToString(separator = "\n") { (url, enabled) ->
            "$url\t$enabled"
        }
        preferences.put("${addonEnabledStatesKey}_$profileId", payload)
    }
}

private fun parseEnabledStateLine(line: String): Pair<String, Boolean>? {
    val url = line.substringBefore("\t").trim().takeIf { it.isNotEmpty() } ?: return null
    val rawEnabled = line.substringAfter("\t", "true").trim().lowercase()
    val enabled = rawEnabled != "false"
    return url to enabled
}

private val httpClient = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(60))
    .followRedirects(HttpClient.Redirect.NORMAL)
    .build()

private val noRedirectHttpClient = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(60))
    .followRedirects(HttpClient.Redirect.NEVER)
    .build()

private fun requestAllowsBody(method: String): Boolean =
    when (method.uppercase()) {
        "POST", "PUT", "PATCH", "DELETE" -> true
        else -> false
    }

private fun Map<String, String>.withoutAcceptEncoding(): Map<String, String> =
    entries
        .filterNot { (key, _) -> key.equals("Accept-Encoding", ignoreCase = true) }
        .associate { (key, value) -> key to value }

private fun Map<String, String>.getHeaderIgnoreCase(name: String): String? =
    entries.firstOrNull { (key, _) -> key.equals(name, ignoreCase = true) }?.value

private suspend fun executeTextRequest(
    method: String,
    url: String,
    headers: Map<String, String> = emptyMap(),
    body: String = "",
): String = withContext(Dispatchers.IO) {
    val normalizedMethod = method.uppercase()
    val sanitizedHeaders = headers.withoutAcceptEncoding()
    
    val requestBuilder = HttpRequest.newBuilder().uri(URI.create(url))
    sanitizedHeaders.forEach { (key, value) ->
        requestBuilder.header(key, value)
    }

    val bodyPublisher = if (requestAllowsBody(normalizedMethod)) {
        if (sanitizedHeaders.getHeaderIgnoreCase("Content-Type") == null) {
            val contentType = if (normalizedMethod == "POST") "application/x-www-form-urlencoded" else "application/json"
            requestBuilder.header("Content-Type", contentType)
        }
        HttpRequest.BodyPublishers.ofString(body, Charsets.UTF_8)
    } else {
        HttpRequest.BodyPublishers.noBody()
    }

    requestBuilder.method(normalizedMethod, bodyPublisher)

    val response = httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString(Charsets.UTF_8))
    if (response.statusCode() !in 200..299) {
        error("HTTP request failed with status ${response.statusCode()}")
    }
    val payload = response.body()
    if (payload.isNullOrBlank()) {
        error("Empty response body")
    }
    payload
}

actual suspend fun httpGetText(url: String): String =
    executeTextRequest(
        method = "GET",
        url = url,
        headers = mapOf("Accept" to "application/json"),
    )

actual suspend fun httpPostJson(url: String, body: String): String =
    executeTextRequest(
        method = "POST",
        url = url,
        headers = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json",
        ),
        body = body,
    )

actual suspend fun httpGetTextWithHeaders(
    url: String,
    headers: Map<String, String>,
): String =
    executeTextRequest(
        method = "GET",
        url = url,
        headers = mapOf("Accept" to "application/json") + headers,
    )

actual suspend fun httpPostJsonWithHeaders(
    url: String,
    body: String,
    headers: Map<String, String>,
): String =
    executeTextRequest(
        method = "POST",
        url = url,
        headers = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/json",
        ) + headers,
        body = body,
    )

actual suspend fun httpRequestRaw(
    method: String,
    url: String,
    headers: Map<String, String>,
    body: String,
    followRedirects: Boolean,
): RawHttpResponse = withContext(Dispatchers.IO) {
    val normalizedMethod = method.uppercase()
    val sanitizedHeaders = headers.withoutAcceptEncoding()

    val requestBuilder = HttpRequest.newBuilder().uri(URI.create(url))
    sanitizedHeaders.forEach { (key, value) ->
        requestBuilder.header(key, value)
    }

    val bodyPublisher = if (requestAllowsBody(normalizedMethod)) {
        if (sanitizedHeaders.getHeaderIgnoreCase("Content-Type") == null) {
            val contentType = if (normalizedMethod == "POST") "application/x-www-form-urlencoded" else "application/json"
            requestBuilder.header("Content-Type", contentType)
        }
        HttpRequest.BodyPublishers.ofString(body, Charsets.UTF_8)
    } else {
        HttpRequest.BodyPublishers.noBody()
    }

    requestBuilder.method(normalizedMethod, bodyPublisher)

    val client = if (followRedirects) httpClient else noRedirectHttpClient
    val response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString(Charsets.UTF_8))

    RawHttpResponse(
        status = response.statusCode(),
        statusText = "HTTP ${response.statusCode()}",
        url = response.uri().toString(),
        body = response.body() ?: "",
        headers = response.headers().map().mapValues { (_, values) ->
            values.joinToString(",")
        }.mapKeys { (name, _) ->
            name.lowercase()
        },
    )
}
