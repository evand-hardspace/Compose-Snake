import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import common.ui.Game
import configs.appWindowSize
import configs.gameType

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = appWindowSize),
        resizable = false
    ) {
        Game(gameType)
    }
}
