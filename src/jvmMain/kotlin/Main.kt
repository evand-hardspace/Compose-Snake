import androidx.compose.ui.window.application
import common.ui.Game
import configs.*

fun main() = application {
    Game(onCloseRequest = ::exitApplication, size = globalAppWindowSize)
}
