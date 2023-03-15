import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import common.ui.Game
import configs.globalAppWindowSize
import configs.globalGameType

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = globalAppWindowSize),
        resizable = false
    ) {
        Game(globalGameType)
    }
}
