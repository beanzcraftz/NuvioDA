import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.nuvio.app.App
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery

fun main() {
    // Attempt to discover native VLC installation
    NativeDiscovery().discover()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Nuvio",
        ) {
            App()
        }
    }
}
