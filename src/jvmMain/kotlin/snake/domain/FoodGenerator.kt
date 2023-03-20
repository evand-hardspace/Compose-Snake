package snake.domain

import common.Field
import common.models.*

class FoodGenerator(
    private val field: Field
) {

    init {
        generateFood()
    }

    //todo remove generating in random way, this is not efficient
    fun generateFood() {
        field.coordinateMatrix
            .flatten()
            .randomOrNull()
            ?.changeTo(CellType.SECONDARY)
            ?.run(field::update)
    }
}