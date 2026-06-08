package com.nuvio.app.features.settings

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object ThemeSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadSelectedTheme(): String? = preferences.get("loadSelectedTheme", null)
    actual fun saveSelectedTheme(themeName: String) { preferences.put("saveSelectedTheme", themeName) }
    actual fun loadAmoledEnabled(): Boolean? = preferences.get("loadAmoledEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveAmoledEnabled(enabled: Boolean) { preferences.put("saveAmoledEnabled", enabled.toString()) }
    actual fun loadLiquidGlassNativeTabBarEnabled(): Boolean? = preferences.get("loadLiquidGlassNativeTabBarEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveLiquidGlassNativeTabBarEnabled(enabled: Boolean) { preferences.put("saveLiquidGlassNativeTabBarEnabled", enabled.toString()) }
    actual fun loadSelectedAppLanguage(): String? = preferences.get("loadSelectedAppLanguage", null)
    actual fun saveSelectedAppLanguage(languageCode: String) { preferences.put("saveSelectedAppLanguage", languageCode) }
    actual fun applySelectedAppLanguage(languageCode: String) { preferences.put("applySelectedAppLanguage", languageCode) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

