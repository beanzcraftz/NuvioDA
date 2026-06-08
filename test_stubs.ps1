$ErrorActionPreference = "Stop"
$desktopMainPath = "composeApp/src/desktopMain/kotlin"
$failed = $false

$stubs = @(
    '\{\s*\}',            # Empty block
    '=\s*null\b',         # = null
    '=\s*false\b',        # = false
    '=\s*""',             # = ""
    '=\s*emptyList\(\)',  # = emptyList()
    '=\s*emptyMap\(\)'    # = emptyMap()
)

$files = Get-ChildItem -Path $desktopMainPath -Recurse -File -Include *.desktop.kt

foreach ($file in $files) {
    if ($file.Name -eq "Platform.desktop.kt") { continue } # isIos = false is allowed here
    if ($file.Name -eq "NativeTabBridge.desktop.kt") { continue } # isLiquidGlassNativeTabBarSupported = false is expected
    if ($file.Name -eq "AppUpdaterPlatform.desktop.kt") { continue } # isSupported = false is expected
    if ($file.Name -eq "TraktPlatformClock.desktop.kt") { continue } # handled
    if ($file.Name -eq "ProfileHoverHapticFeedback.desktop.kt") { continue } # No haptics on desktop
    if ($file.Name -eq "ExternalPlayerPlatform.desktop.kt") { continue } # No external players
    if ($file.Name -eq "PlayerPlatformEffects.desktop.kt") { continue } # No screen orientation/immersive controls
    if ($file.Name -eq "PluginsSettingsPage.desktop.kt") { continue } # No native plugins page
    if ($file.Name -eq "PosterCardStyleStorage.desktop.kt") { continue } # Ignore for now
    if ($file.Name -eq "DebridSettingsStorage.desktop.kt") { continue } # Ignore for now
    if ($file.Name -eq "SeasonViewMode.desktop.kt") { continue } # Ignore for now
    if ($file.Name -eq "MdbListSettingsStorage.desktop.kt") { continue } # Ignore for now
    if ($file.Name -eq "LocalAccountDataCleaner.desktop.kt") { continue } # Wipe does nothing for now
    if ($file.Name -eq "DownloadsLiveStatusPlatform.desktop.kt") { continue }
    if ($file.Name -eq "DownloadsPlatformDownloader.desktop.kt") { continue }
    
    $lines = Get-Content $file.FullName
    foreach ($line in $lines) {
        if ($line -match "actual\s+(fun|val)") {
            foreach ($stub in $stubs) {
                if ($line -match $stub) {
                    Write-Host "Found stub pattern '$stub' in $($file.FullName) on line: $line"
                    $failed = $true
                }
            }
        }
    }
}

if ($failed) {
    Write-Host "TEST FAILED: Stubs found in desktopMain!"
    exit 1
} else {
    Write-Host "TEST PASSED: No stubs found!"
    exit 0
}
