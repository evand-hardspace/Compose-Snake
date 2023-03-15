package snake.domain

import common.Field
import common.models.*
import common.models.Side.*

class Snake(
    private var head: Coordinate,
    private val field: Field,
    private val onMove: (List<Intent?>) -> Unit,
    private val onAteFood: () -> Unit,
) {

    private var tail: Coordinate? = null

    var headDirection: Side = UP

    fun move() {
        tail = head
        val nextToHeadCell = when (headDirection) {
            RIGHT -> head.rightOrOpposite
            LEFT -> head.leftOrOpposite
            UP -> head.upOrOpposite
            DOWN -> head.downOrOpposite
        }
        if(field.getCellType(nextToHeadCell) == CellType.SECONDARY) onAteFood()
        head = nextToHeadCell
        updateField()
    }

    private fun updateField() {
        listOf(
            head changeTo CellType.MAIN,
            tail?.let { it changeTo CellType.EMPTY }
        ).run(onMove)
    }
}