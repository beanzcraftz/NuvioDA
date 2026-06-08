package com.nuvio.app.features.trakt

import kotlinx.serialization.json.JsonObject



internal actual object TraktCommentsStorage {
    actual fun loadEnabled(): Boolean? = null
    actual fun saveEnabled(enabled: Boolean) { }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

