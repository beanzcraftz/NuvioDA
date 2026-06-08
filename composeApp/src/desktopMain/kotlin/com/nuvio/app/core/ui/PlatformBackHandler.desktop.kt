package com.nuvio.app.core.ui

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    // Desktop does not have a system back button, so this can be a no-op
    // unless you want to map a keyboard shortcut like Esc or Backspace.
}
