package snake.domain

import common.Field
import common.models.*
import common.models.Side.*

class Snake(
    private val field: Field,
    private val onAteFood: () -> Unit,
    private val onGameOver: () -> Unit,
) {

    private val body = mutableListOf(
        (field.size / 2).x + (field.size / 2).y + field.size
    )

    var headDirection: Side = UP

    fun move() {
        val nextCell = getNextCell()
        val nextCellType = field.getCellType(nextCell)
        if (nextCellType == CellType.MAIN) onGameOver()
        if (nextCellType == CellType.SECONDARY) onAteFood()
        body += nextCell
        sendIntents(nextCellType == CellType.SECONDARY)
    }

    private fun getNextCell() = when (headDirection) {
        RIGHT -> body.head.rightOrOpposite
        LEFT -> body.head.leftOrOpposite
        UP -> body.head.upOrOpposite
        DOWN -> body.head.downOrOpposite
    }

    private fun sendIntents(isNextCellIsFood: Boolean) = listOf(
        body.head changeTo CellType.MAIN,
        if (isNextCellIsFood) null else body.removeTail changeTo CellType.EMPTY
    ).run(field::update)
}

private val List<Coordinate>.head: Coordinate get() = last()
private val MutableList<Coordinate>.removeTail: Coordinate get() = removeFirst()