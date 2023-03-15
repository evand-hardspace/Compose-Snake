package snake.domain

import common.FieldController
import common.models.*

class SnakeFieldController : FieldController() {

    private val foodGenerator = FoodGenerator(fieldSize = field.size, onGeneratedFood = field::update)

    private val snake = Snake(
        head = (field.size / 2).x + (field.size / 2).y + field.size,
        field = field,
        onMove = field::update,
        onAteFood = foodGenerator::generateFood
    )

    override fun run() {
        snake.move()
    }

    override val tactDuration: Long get() = 100

    override fun onRight() {
        snake.headDirection = Side.RIGHT
    }

    override fun onLeft() {
        snake.headDirection = Side.LEFT
    }

    override fun onUp() {
        snake.headDirection = Side.UP
    }

    override fun onDown() {
        snake.headDirection = Side.DOWN
    }
}