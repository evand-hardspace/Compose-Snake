package snake.domain

import common.Field
import common.models.*

class FoodGenerator(
    private val field: Field
) {

    init {
        generateFood()
    }

    fun generateFood() {
        field.typedMatrix
            .flatten()
            .filter { it.type == CellType.EMPTY }
            .randomOrNull()
            ?.coordinate
            ?.changeTo(CellType.SECONDARY)
            ?.run(field::update)
    }
}