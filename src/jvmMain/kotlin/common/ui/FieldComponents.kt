package common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import snake.domain.models.CellType

@Composable
fun FieldComponent(field: List<List<CellType>>, modifier: Modifier = Modifier) = Box(modifier = modifier
    .size(308.dp)
    .background(color = Color.Black),
    contentAlignment = Alignment.Center) {
    Box(modifier = modifier.size(300.dp)) {
        Column {
            field.forEach { rows ->
                Row {
                    rows.forEach {
                        Cell(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(type: CellType) = Box(contentAlignment = Alignment.Center) {
    val color = when (type) {
        CellType.EMPTY -> Color.White
        CellType.SNAKE -> Color.Green
        CellType.FOOD -> Color.Red
    }
    Box(modifier = Modifier
        .size(15.dp)
        .background(color = Color.Black))
    Box(modifier = Modifier
        .size(14.dp)
        .background(color = color))

}