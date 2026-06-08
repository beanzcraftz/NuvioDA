package com.nuvio.app.features.player

import java.util.prefs.Preferences
import kotlinx.serialization.json.JsonObject



internal actual object PlayerSettingsStorage {
    private val preferences = Preferences.userRoot().node("nuvio_desktop")
    actual fun loadShowLoadingOverlay(): Boolean? = preferences.get("loadShowLoadingOverlay", null)?.toBooleanStrictOrNull()
    actual fun saveShowLoadingOverlay(enabled: Boolean) { preferences.put("saveShowLoadingOverlay", enabled.toString()) }
    actual fun loadResizeMode(): String? = preferences.get("loadResizeMode", null)
    actual fun saveResizeMode(mode: String) { preferences.put("saveResizeMode", mode) }
    actual fun loadHoldToSpeedEnabled(): Boolean? = preferences.get("loadHoldToSpeedEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveHoldToSpeedEnabled(enabled: Boolean) { preferences.put("saveHoldToSpeedEnabled", enabled.toString()) }
    actual fun loadHoldToSpeedValue(): Float? = preferences.get("loadHoldToSpeedValue", null)?.toFloatOrNull()
    actual fun saveHoldToSpeedValue(speed: Float) { preferences.put("saveHoldToSpeedValue", speed.toString()) }
    actual fun loadExternalPlayerEnabled(): Boolean? = preferences.get("loadExternalPlayerEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveExternalPlayerEnabled(enabled: Boolean) { preferences.put("saveExternalPlayerEnabled", enabled.toString()) }
    actual fun loadExternalPlayerForwardSubtitles(): Boolean? = preferences.get("loadExternalPlayerForwardSubtitles", null)?.toBooleanStrictOrNull()
    actual fun saveExternalPlayerForwardSubtitles(enabled: Boolean) { preferences.put("saveExternalPlayerForwardSubtitles", enabled.toString()) }
    actual fun loadExternalPlayerId(): String? = preferences.get("loadExternalPlayerId", null)
    actual fun saveExternalPlayerId(playerId: String?) { if (playerId != null) preferences.put("ExternalPlayerId", playerId) else preferences.remove("ExternalPlayerId") }
    actual fun loadPreferredAudioLanguage(): String? = preferences.get("loadPreferredAudioLanguage", null)
    actual fun savePreferredAudioLanguage(language: String) { preferences.put("savePreferredAudioLanguage", language) }
    actual fun loadSecondaryPreferredAudioLanguage(): String? = preferences.get("loadSecondaryPreferredAudioLanguage", null)
    actual fun saveSecondaryPreferredAudioLanguage(language: String?) { if (language != null) preferences.put("SecondaryPreferredAudioLanguage", language) else preferences.remove("SecondaryPreferredAudioLanguage") }
    actual fun loadPreferredSubtitleLanguage(): String? = preferences.get("loadPreferredSubtitleLanguage", null)
    actual fun savePreferredSubtitleLanguage(language: String) { preferences.put("savePreferredSubtitleLanguage", language) }
    actual fun loadSecondaryPreferredSubtitleLanguage(): String? = preferences.get("loadSecondaryPreferredSubtitleLanguage", null)
    actual fun saveSecondaryPreferredSubtitleLanguage(language: String?) { if (language != null) preferences.put("SecondaryPreferredSubtitleLanguage", language) else preferences.remove("SecondaryPreferredSubtitleLanguage") }
    actual fun loadSubtitleTextColor(): String? = preferences.get("loadSubtitleTextColor", null)
    actual fun saveSubtitleTextColor(colorHex: String) { preferences.put("saveSubtitleTextColor", colorHex) }
    actual fun loadSubtitleBackgroundColor(): String? = preferences.get("loadSubtitleBackgroundColor", null)
    actual fun saveSubtitleBackgroundColor(colorHex: String) { preferences.put("saveSubtitleBackgroundColor", colorHex) }
    actual fun loadSubtitleOutlineColor(): String? = preferences.get("loadSubtitleOutlineColor", null)
    actual fun saveSubtitleOutlineColor(colorHex: String) { preferences.put("saveSubtitleOutlineColor", colorHex) }
    actual fun loadSubtitleOutlineEnabled(): Boolean? = preferences.get("loadSubtitleOutlineEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveSubtitleOutlineEnabled(enabled: Boolean) { preferences.put("saveSubtitleOutlineEnabled", enabled.toString()) }
    actual fun loadSubtitleOutlineWidth(): Int? = preferences.get("loadSubtitleOutlineWidth", null)?.toIntOrNull()
    actual fun saveSubtitleOutlineWidth(width: Int) { preferences.put("saveSubtitleOutlineWidth", width.toString()) }
    actual fun loadSubtitleBold(): Boolean? = preferences.get("loadSubtitleBold", null)?.toBooleanStrictOrNull()
    actual fun saveSubtitleBold(enabled: Boolean) { preferences.put("saveSubtitleBold", enabled.toString()) }
    actual fun loadSubtitleFontSizeSp(): Int? = preferences.get("loadSubtitleFontSizeSp", null)?.toIntOrNull()
    actual fun saveSubtitleFontSizeSp(fontSizeSp: Int) { preferences.put("saveSubtitleFontSizeSp", fontSizeSp.toString()) }
    actual fun loadSubtitleBottomOffset(): Int? = preferences.get("loadSubtitleBottomOffset", null)?.toIntOrNull()
    actual fun saveSubtitleBottomOffset(bottomOffset: Int) { preferences.put("saveSubtitleBottomOffset", bottomOffset.toString()) }
    actual fun loadSubtitleUseForcedSubtitles(): Boolean? = preferences.get("loadSubtitleUseForcedSubtitles", null)?.toBooleanStrictOrNull()
    actual fun saveSubtitleUseForcedSubtitles(enabled: Boolean) { preferences.put("saveSubtitleUseForcedSubtitles", enabled.toString()) }
    actual fun loadSubtitleShowOnlyPreferredLanguages(): Boolean? = preferences.get("loadSubtitleShowOnlyPreferredLanguages", null)?.toBooleanStrictOrNull()
    actual fun saveSubtitleShowOnlyPreferredLanguages(enabled: Boolean) { preferences.put("saveSubtitleShowOnlyPreferredLanguages", enabled.toString()) }
    actual fun loadAddonSubtitleStartupMode(): String? = preferences.get("loadAddonSubtitleStartupMode", null)
    actual fun saveAddonSubtitleStartupMode(mode: String) { preferences.put("saveAddonSubtitleStartupMode", mode) }
    actual fun loadStreamReuseLastLinkEnabled(): Boolean? = preferences.get("loadStreamReuseLastLinkEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveStreamReuseLastLinkEnabled(enabled: Boolean) { preferences.put("saveStreamReuseLastLinkEnabled", enabled.toString()) }
    actual fun loadStreamReuseLastLinkCacheHours(): Int? = preferences.get("loadStreamReuseLastLinkCacheHours", null)?.toIntOrNull()
    actual fun saveStreamReuseLastLinkCacheHours(hours: Int) { preferences.put("saveStreamReuseLastLinkCacheHours", hours.toString()) }
    actual fun loadDecoderPriority(): Int? = preferences.get("loadDecoderPriority", null)?.toIntOrNull()
    actual fun saveDecoderPriority(priority: Int) { preferences.put("saveDecoderPriority", priority.toString()) }
    actual fun loadMapDV7ToHevc(): Boolean? = preferences.get("loadMapDV7ToHevc", null)?.toBooleanStrictOrNull()
    actual fun saveMapDV7ToHevc(enabled: Boolean) { preferences.put("saveMapDV7ToHevc", enabled.toString()) }
    actual fun loadTunnelingEnabled(): Boolean? = preferences.get("loadTunnelingEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveTunnelingEnabled(enabled: Boolean) { preferences.put("saveTunnelingEnabled", enabled.toString()) }
    actual fun loadStreamAutoPlayMode(): String? = preferences.get("loadStreamAutoPlayMode", null)
    actual fun saveStreamAutoPlayMode(mode: String) { preferences.put("saveStreamAutoPlayMode", mode) }
    actual fun loadStreamAutoPlaySource(): String? = preferences.get("loadStreamAutoPlaySource", null)
    actual fun saveStreamAutoPlaySource(source: String) { preferences.put("saveStreamAutoPlaySource", source) }
    actual fun loadStreamAutoPlaySelectedAddons(): Set<String>? = preferences.get("StreamAutoPlaySelectedAddons", null)?.split(",")?.toSet()
    actual fun saveStreamAutoPlaySelectedAddons(addons: Set<String>) { preferences.put("StreamAutoPlaySelectedAddons", addons.joinToString(",")) }
    actual fun loadStreamAutoPlaySelectedPlugins(): Set<String>? = preferences.get("StreamAutoPlaySelectedPlugins", null)?.split(",")?.toSet()
    actual fun saveStreamAutoPlaySelectedPlugins(plugins: Set<String>) { preferences.put("StreamAutoPlaySelectedPlugins", plugins.joinToString(",")) }
    actual fun loadStreamAutoPlayRegex(): String? = preferences.get("loadStreamAutoPlayRegex", null)
    actual fun saveStreamAutoPlayRegex(regex: String) { preferences.put("saveStreamAutoPlayRegex", regex) }
    actual fun loadStreamAutoPlayTimeoutSeconds(): Int? = preferences.get("loadStreamAutoPlayTimeoutSeconds", null)?.toIntOrNull()
    actual fun saveStreamAutoPlayTimeoutSeconds(seconds: Int) { preferences.put("saveStreamAutoPlayTimeoutSeconds", seconds.toString()) }
    actual fun loadSkipIntroEnabled(): Boolean? = preferences.get("loadSkipIntroEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveSkipIntroEnabled(enabled: Boolean) { preferences.put("saveSkipIntroEnabled", enabled.toString()) }
    actual fun loadAnimeSkipEnabled(): Boolean? = preferences.get("loadAnimeSkipEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveAnimeSkipEnabled(enabled: Boolean) { preferences.put("saveAnimeSkipEnabled", enabled.toString()) }
    actual fun loadAnimeSkipClientId(): String? = preferences.get("loadAnimeSkipClientId", null)
    actual fun saveAnimeSkipClientId(clientId: String) { preferences.put("saveAnimeSkipClientId", clientId) }

    actual fun loadIntroDbApiKey(): String? = preferences.get("loadIntroDbApiKey", null)
    actual fun saveIntroDbApiKey(apiKey: String) { preferences.put("saveIntroDbApiKey", apiKey) }
    actual fun loadIntroSubmitEnabled(): Boolean? = preferences.get("loadIntroSubmitEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIntroSubmitEnabled(enabled: Boolean) { preferences.put("saveIntroSubmitEnabled", enabled.toString()) }
    actual fun loadStreamAutoPlayNextEpisodeEnabled(): Boolean? = preferences.get("loadStreamAutoPlayNextEpisodeEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveStreamAutoPlayNextEpisodeEnabled(enabled: Boolean) { preferences.put("saveStreamAutoPlayNextEpisodeEnabled", enabled.toString()) }
    actual fun loadStreamAutoPlayPreferBingeGroup(): Boolean? = preferences.get("loadStreamAutoPlayPreferBingeGroup", null)?.toBooleanStrictOrNull()
    actual fun saveStreamAutoPlayPreferBingeGroup(enabled: Boolean) { preferences.put("saveStreamAutoPlayPreferBingeGroup", enabled.toString()) }
    actual fun loadStreamAutoPlayReuseBingeGroup(): Boolean? = preferences.get("loadStreamAutoPlayReuseBingeGroup", null)?.toBooleanStrictOrNull()
    actual fun saveStreamAutoPlayReuseBingeGroup(enabled: Boolean) { preferences.put("saveStreamAutoPlayReuseBingeGroup", enabled.toString()) }
    actual fun loadNextEpisodeThresholdMode(): String? = preferences.get("loadNextEpisodeThresholdMode", null)
    actual fun saveNextEpisodeThresholdMode(mode: String) { preferences.put("saveNextEpisodeThresholdMode", mode) }
    actual fun loadNextEpisodeThresholdPercent(): Float? = preferences.get("loadNextEpisodeThresholdPercent", null)?.toFloatOrNull()
    actual fun saveNextEpisodeThresholdPercent(percent: Float) { preferences.put("saveNextEpisodeThresholdPercent", percent.toString()) }
    actual fun loadNextEpisodeThresholdMinutesBeforeEnd(): Float? = preferences.get("loadNextEpisodeThresholdMinutesBeforeEnd", null)?.toFloatOrNull()
    actual fun saveNextEpisodeThresholdMinutesBeforeEnd(minutes: Float) { preferences.put("saveNextEpisodeThresholdMinutesBeforeEnd", minutes.toString()) }
    actual fun loadUseLibass(): Boolean? = preferences.get("loadUseLibass", null)?.toBooleanStrictOrNull()
    actual fun saveUseLibass(enabled: Boolean) { preferences.put("saveUseLibass", enabled.toString()) }
    actual fun loadLibassRenderType(): String? = preferences.get("loadLibassRenderType", null)
    actual fun saveLibassRenderType(renderType: String) { preferences.put("saveLibassRenderType", renderType) }
    actual fun loadIosVideoOutputPreset(): String? = preferences.get("loadIosVideoOutputPreset", null)
    actual fun saveIosVideoOutputPreset(preset: String) { preferences.put("saveIosVideoOutputPreset", preset) }
    actual fun loadIosToneMappingMode(): String? = preferences.get("loadIosToneMappingMode", null)
    actual fun saveIosToneMappingMode(mode: String) { preferences.put("saveIosToneMappingMode", mode) }
    actual fun loadIosTargetPrimaries(): String? = preferences.get("loadIosTargetPrimaries", null)
    actual fun saveIosTargetPrimaries(primaries: String) { preferences.put("saveIosTargetPrimaries", primaries) }
    actual fun loadIosTargetTransfer(): String? = preferences.get("loadIosTargetTransfer", null)
    actual fun saveIosTargetTransfer(transfer: String) { preferences.put("saveIosTargetTransfer", transfer) }
    actual fun loadIosHardwareDecoderMode(): String? = preferences.get("loadIosHardwareDecoderMode", null)
    actual fun saveIosHardwareDecoderMode(mode: String) { preferences.put("saveIosHardwareDecoderMode", mode) }
    actual fun loadIosAudioOutputMode(): String? = preferences.get("loadIosAudioOutputMode", null)
    actual fun saveIosAudioOutputMode(mode: String) { preferences.put("saveIosAudioOutputMode", mode) }
    actual fun loadIosExtendedDynamicRangeEnabled(): Boolean? = preferences.get("loadIosExtendedDynamicRangeEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIosExtendedDynamicRangeEnabled(enabled: Boolean) { preferences.put("saveIosExtendedDynamicRangeEnabled", enabled.toString()) }
    actual fun loadIosTargetColorspaceHintEnabled(): Boolean? = preferences.get("loadIosTargetColorspaceHintEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIosTargetColorspaceHintEnabled(enabled: Boolean) { preferences.put("saveIosTargetColorspaceHintEnabled", enabled.toString()) }
    actual fun loadIosHdrComputePeakEnabled(): Boolean? = preferences.get("loadIosHdrComputePeakEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIosHdrComputePeakEnabled(enabled: Boolean) { preferences.put("saveIosHdrComputePeakEnabled", enabled.toString()) }
    actual fun loadIosDebandEnabled(): Boolean? = preferences.get("loadIosDebandEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIosDebandEnabled(enabled: Boolean) { preferences.put("saveIosDebandEnabled", enabled.toString()) }
    actual fun loadIosInterpolationEnabled(): Boolean? = preferences.get("loadIosInterpolationEnabled", null)?.toBooleanStrictOrNull()
    actual fun saveIosInterpolationEnabled(enabled: Boolean) { preferences.put("saveIosInterpolationEnabled", enabled.toString()) }
    actual fun loadIosBrightness(): Int? = preferences.get("loadIosBrightness", null)?.toIntOrNull()
    actual fun saveIosBrightness(value: Int) { preferences.put("saveIosBrightness", value.toString()) }
    actual fun loadIosContrast(): Int? = preferences.get("loadIosContrast", null)?.toIntOrNull()
    actual fun saveIosContrast(value: Int) { preferences.put("saveIosContrast", value.toString()) }
    actual fun loadIosSaturation(): Int? = preferences.get("loadIosSaturation", null)?.toIntOrNull()
    actual fun saveIosSaturation(value: Int) { preferences.put("saveIosSaturation", value.toString()) }
    actual fun loadIosGamma(): Int? = preferences.get("loadIosGamma", null)?.toIntOrNull()
    actual fun saveIosGamma(value: Int) { preferences.put("saveIosGamma", value.toString()) }
    actual fun exportToSyncPayload(): kotlinx.serialization.json.JsonObject = kotlinx.serialization.json.JsonObject(emptyMap())
    actual fun replaceFromSyncPayload(payload: JsonObject) { }
}

