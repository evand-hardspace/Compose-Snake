@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package common.ui

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import common.GameEngine
import common.models.Side
import configs.globalGameType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Game(onCloseRequest: () -> Unit, size: DpSize) {
    val gameEngine = remember { GameEngine.provide(globalGameType) }
    val matrix by gameEngine.field.matrix.collectAsState()

    LaunchedEffect(Unit) {
        gameEngine.start()
    }

    Window(
        onCloseRequest = onCloseRequest,
        state = WindowState(size = size),
        title = globalGameType.gameName,
        onKeyEvent = {
            when(it.key) {
                Key.DirectionDown -> gameEngine.click(Side.DOWN)
                Key.DirectionUp -> gameEngine.click(Side.UP)
                Key.DirectionLeft -> gameEngine.click(Side.LEFT)
                Key.DirectionRight -> gameEngine.click(Side.RIGHT)
            }
            true
        },
        resizable = false
    ) {
        FieldComponent(matrix)
    }
}