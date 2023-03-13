package common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.FieldController
import configs.GameType
import snake.domain.models.Side

@Composable
fun Game(gameType: GameType, modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val fieldController = remember { FieldController.provide(gameType) }
    val matrix by fieldController.field.matrix.collectAsState()

    FieldComponent(matrix)
    Spacer(modifier = Modifier.height(100.dp))
    ControllerComponent(
        onUp = { fieldController.click(Side.UP) },
        onDown = { fieldController.click(Side.DOWN) },
        onLeft = { fieldController.click(Side.LEFT) },
        onRight = { fieldController.click(Side.RIGHT) }
    )
    Spacer(modifier = Modifier.height(30.dp))
}