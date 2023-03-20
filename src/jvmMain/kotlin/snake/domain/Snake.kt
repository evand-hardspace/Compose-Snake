package snake.domain

import common.Field
import common.models.*
import common.models.Side.*

class Snake(
    private val field: Field,
    private val foodGenerator: FoodGenerator = FoodGenerator(field = field),
    private val onGameOver: () -> Unit,
) {

    private var head = Direction(UP)
    private var isNextCellFood: Boolean = false
    private val body = mutableListOf(
        (field.size / 2).x + (field.size / 2).y + field.size
    )

    fun move() {
        head.actualize()
        val nextCell = getNextCell()
        val nextCellType = field.getCellType(nextCell)
        if (nextCellType == CellType.MAIN) onGameOver()
        isNextCellFood = nextCellType == CellType.SECONDARY
        if (isNextCellFood) foodGenerator.generateFood()
        body += nextCell
        sendIntents()
    }

    fun turn(side: Side) {
        if (head.direction != side.opposite) head.intendDirection = side
    }

    private fun getNextCell() = when (head.direction) {
        RIGHT -> body.head.rightOrOpposite
        LEFT -> body.head.leftOrOpposite
        UP -> body.head.upOrOpposite
        DOWN -> body.head.downOrOpposite
    }

    private fun sendIntents(): Unit = listOf(
        body.head changeTo CellType.MAIN,
        if (isNextCellFood) null else body.removeTail changeTo CellType.EMPTY
    ).run(field::update)
}

private val List<Coordinate>.head: Coordinate get() = last()
private val MutableList<Coordinate>.removeTail: Coordinate get() = removeFirst()