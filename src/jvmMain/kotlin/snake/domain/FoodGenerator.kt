package snake.domain

import common.models.*
import kotlin.random.Random
import kotlin.random.nextInt

class FoodGenerator(
    private val fieldSize: Int,
    private val random: Random = Random,
    private val onGeneratedFood: (Intent) -> Unit,
) {

    init { generateFood() }

    fun generateFood() {
        (random.nextInt(
            0 until fieldSize
        ).x + random.nextInt(
            0 until fieldSize
        ).y + fieldSize changeTo CellType.SECONDARY).run(onGeneratedFood)
    }
}