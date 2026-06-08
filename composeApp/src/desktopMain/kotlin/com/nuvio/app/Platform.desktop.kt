package com.nuvio.app

import java.util.prefs.Preferences

internal actual val isIos: Boolean = false

class DesktopPlatform : Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()
