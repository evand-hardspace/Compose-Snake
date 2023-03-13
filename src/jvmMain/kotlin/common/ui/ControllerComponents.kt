package common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import snake.domain.models.Side

@Composable
fun ControllerComponent(
    onUp: () -> Unit,
    onDown: () -> Unit,
    onLeft: () -> Unit,
    onRight: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        ControllerButton(type = Side.UP, onUp)
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            ControllerButton(type = Side.LEFT, onLeft)
            ControllerButton(type = Side.RIGHT, onRight)
        }
        ControllerButton(type = Side.DOWN, onDown)
    }
}

@Composable
fun ControllerButton(type: Side, onClick: () -> Unit) = when (type) {
    Side.RIGHT -> Image(painter = painterResource("/arrow_right.png"),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(80.dp))
    Side.LEFT -> Image(painter = painterResource("/arrow_left.png"),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(80.dp))
    Side.UP -> Image(painter = painterResource("/arrow_up.png"),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(80.dp))
    Side.DOWN -> Image(painter = painterResource("/arrow_down.png"),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(80.dp))
}