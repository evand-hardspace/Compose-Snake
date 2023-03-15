package common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.GameEngine
import configs.GameType
import common.models.Side

@Composable
fun Game(gameType: GameType, modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val gameEngine = remember { GameEngine.provide(gameType) }
    val matrix by gameEngine.field.matrix.collectAsState()

    LaunchedEffect(Unit) {
        gameEngine.start()
    }

    FieldComponent(matrix)
    Spacer(modifier = Modifier.height(100.dp))
    ControllerComponent(
        onUp = { gameEngine.click(Side.UP) },
        onDown = { gameEngine.click(Side.DOWN) },
        onLeft = { gameEngine.click(Side.LEFT) },
        onRight = { gameEngine.click(Side.RIGHT) }
    )
    Spacer(modifier = Modifier.height(30.dp))
}