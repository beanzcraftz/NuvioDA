package com.nuvio.app.core.sync

import kotlinx.coroutines.flow.Flow



internal actual object AppForegroundMonitor {
    actual fun events(): kotlinx.coroutines.flow.Flow<Unit> = kotlinx.coroutines.flow.emptyFlow()
}

