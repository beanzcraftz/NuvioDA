package com.nuvio.app.features.player

import java.util.prefs.Preferences
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import kotlinx.coroutines.delay
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.Component

@Composable
actual fun PlatformPlayerSurface(
    sourceUrl: String,
    sourceAudioUrl: String?,
    sourceHeaders: Map<String, String>,
    sourceResponseHeaders: Map<String, String>,
    useYoutubeChunkedPlayback: Boolean,
    modifier: Modifier,
    playWhenReady: Boolean,
    resizeMode: PlayerResizeMode,
    useNativeController: Boolean,
    onControllerReady: (PlayerEngineController) -> Unit,
    onSnapshot: (PlayerPlaybackSnapshot) -> Unit,
    onError: (String?) -> Unit,
) {
    val mediaPlayerComponent = remember { EmbeddedMediaPlayerComponent() }
    
    DisposableEffect(Unit) {
        val player = mediaPlayerComponent.mediaPlayer()
        
        val controller = object : PlayerEngineController {
            override fun play() { player.controls().play() }
            override fun pause() { player.controls().pause() }
            override fun seekTo(positionMs: Long) { player.controls().setTime(positionMs) }
            override fun seekBy(offsetMs: Long) { player.controls().skipTime(offsetMs) }
            override fun retry() { player.controls().play() }
            override fun setPlaybackSpeed(speed: Float) { player.controls().setRate(speed) }
            override fun setMuted(muted: Boolean) { player.audio().isMute = muted }
            
            override fun getAudioTracks(): List<AudioTrack> = player.audio().trackDescriptions().mapIndexed { index, track ->
                AudioTrack(id = track.id().toString(), label = track.description() ?: "Track $index", index = index, isSelected = false)
            }
            override fun getSubtitleTracks(): List<SubtitleTrack> = player.subpictures().trackDescriptions().mapIndexed { index, track ->
                SubtitleTrack(id = track.id().toString(), label = track.description() ?: "Track $index", index = index, isSelected = false)
            }
            override fun selectAudioTrack(index: Int) { 
                val tracks = player.audio().trackDescriptions()
                if (index in tracks.indices) player.audio().setTrack(tracks[index].id())
            }
            override fun selectSubtitleTrack(index: Int) {
                val tracks = player.subpictures().trackDescriptions()
                if (index in tracks.indices) player.subpictures().setTrack(tracks[index].id())
            }
            override fun setSubtitleUri(url: String) { player.subpictures().setSubTitleFile(url) }
            override fun clearExternalSubtitle() { player.subpictures().setTrack(-1) }
            override fun clearExternalSubtitleAndSelect(trackIndex: Int) {
                clearExternalSubtitle()
                selectSubtitleTrack(trackIndex)
            }
        }
        
        onControllerReady(controller)
        
        player.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun playing(mediaPlayer: MediaPlayer) {
                onSnapshot(
                    PlayerPlaybackSnapshot(
                        isLoading = false,
                        isPlaying = true,
                        positionMs = mediaPlayer.status().time(),
                        durationMs = mediaPlayer.status().length()
                    )
                )
            }
            override fun paused(mediaPlayer: MediaPlayer) {
                onSnapshot(
                    PlayerPlaybackSnapshot(
                        isLoading = false,
                        isPlaying = false,
                        positionMs = mediaPlayer.status().time(),
                        durationMs = mediaPlayer.status().length()
                    )
                )
            }
            override fun timeChanged(mediaPlayer: MediaPlayer, newTime: Long) {
                onSnapshot(
                    PlayerPlaybackSnapshot(
                        isLoading = false,
                        isPlaying = mediaPlayer.status().isPlaying,
                        positionMs = newTime,
                        durationMs = mediaPlayer.status().length()
                    )
                )
            }
            override fun error(mediaPlayer: MediaPlayer) {
                onError("VLC Player Error")
            }
        })
        
        val options = mutableListOf<String>()
        if (sourceHeaders.isNotEmpty()) {
            val headerStr = sourceHeaders.entries.joinToString("\r\n") { "${it.key}: ${it.value}" }
            options.add(":http-user-agent=${sourceHeaders["User-Agent"] ?: "vlc"}")
            // Adding custom headers is limited in VLC, usually requires specific format
        }
        
        player.media().startPaused(sourceUrl, *options.toTypedArray())
        if (playWhenReady) {
            player.controls().play()
        }
        
        onDispose {
            mediaPlayerComponent.release()
        }
    }
    
    SwingPanel(
        background = androidx.compose.ui.graphics.Color.Black,
        modifier = modifier,
        factory = {
            mediaPlayerComponent as Component
        }
    )
}
