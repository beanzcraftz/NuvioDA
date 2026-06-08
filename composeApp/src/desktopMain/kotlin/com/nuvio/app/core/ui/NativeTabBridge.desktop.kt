package com.nuvio.app.core.ui

import java.util.prefs.Preferences

private val preferences = Preferences.userRoot().node("nuvio_desktop")

internal actual fun isLiquidGlassNativeTabBarSupported(): Boolean = preferences.getBoolean("isLiquidGlassNativeTabBarSupported", false)

internal actual fun publishLiquidGlassNativeTabBarEnabled(enabled: Boolean) { preferences.put("publishLiquidGlassNativeTabBarEnabled", enabled.toString()) }

internal actual fun publishNativeTabBarVisible(visible: Boolean) { preferences.put("publishNativeTabBarVisible", visible.toString()) }

internal actual fun publishNativeSelectedTab(tabName: String) { preferences.put("publishNativeSelectedTab", tabName) }

internal actual fun publishNativeTabAccentColor(hexColor: String) { preferences.put("publishNativeTabAccentColor", hexColor) }

internal actual fun publishNativeProfileTabIcon(
    name: String?,
    avatarColorHex: String?,
    avatarImageUrl: String?,
    avatarBackgroundColorHex: String?,
) {}
