package snake.domain

import common.Field
import common.models.*
import kotlin.random.Random
import kotlin.random.nextInt

class FoodGenerator(
    private val random: Random = Random,
    private val field: Field
) {

    init {
        generateFood()
    }

    //todo remove generating in random way, this is not efficient
    fun generateFood() {
        var potentialCoordinate = generateRandomCoordinate()
        while (field.getCellType(potentialCoordinate) != CellType.EMPTY) {
            potentialCoordinate = generateRandomCoordinate()
        }

        (potentialCoordinate changeTo CellType.SECONDARY).run(field::update)
    }

    private fun generateRandomCoordinate(): Coordinate = random.nextInt(
        0 until field.size
    ).x + random.nextInt(
        0 until field.size
    ).y + field.size
}