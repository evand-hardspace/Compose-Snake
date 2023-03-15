package snake.domain

import common.models.*
import common.models.Side.*

class Snake(
    private var head: Coordinate,
    private val onMove: (List<Intent?>) -> Unit
) {

    private var tail: Coordinate? = null

    var direction: Side = UP

    fun move() {
        tail = head
        head = when (direction) {
            RIGHT -> head.rightOrOpposite
            LEFT -> head.leftOrOpposite
            UP -> head.upOrOpposite
            DOWN -> head.downOrOpposite
        }
        listOf(
            head changeTo CellType.MAIN,
            tail?.let { it changeTo CellType.EMPTY }
        ).run(onMove)
    }
}