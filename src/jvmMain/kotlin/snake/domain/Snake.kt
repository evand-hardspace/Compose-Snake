package snake.domain

import common.Field
import common.models.*
import common.models.Side.*

class Snake(
    head: Coordinate,
    private val field: Field,
    private val onMove: (List<Intent?>) -> Unit,
    private val onAteFood: () -> Unit,
) {

    private val body = mutableListOf(head)

    var headDirection: Side = UP

    fun move() {
        val nextToHeadCell = getNextCell()
        val isNextCellIsFood = field.getCellType(nextToHeadCell) == CellType.SECONDARY
        if(isNextCellIsFood) onAteFood()
        body += nextToHeadCell
        sendIntents(isNextCellIsFood)
    }

    private fun getNextCell() = when (headDirection) {
        RIGHT -> body.head.rightOrOpposite
        LEFT -> body.head.leftOrOpposite
        UP -> body.head.upOrOpposite
        DOWN -> body.head.downOrOpposite
    }

    private fun sendIntents(isNextCellIsFood: Boolean) = listOf(
        body.head changeTo CellType.MAIN,
        if(isNextCellIsFood) null else body.removeTail changeTo CellType.EMPTY
    ).run(onMove)
}

private val List<Coordinate>.head: Coordinate get() = last()
private val MutableList<Coordinate>.removeTail: Coordinate get() = removeFirst()