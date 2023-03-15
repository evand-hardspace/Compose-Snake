package snake.domain

import common.FieldController
import common.models.*

class SnakeFieldController : FieldController() {

    private val snake = Snake(
        head = (field.size / 2).x + (field.size / 2).y + field.size,
    ) {
        field.update(intents = it)
    }

    override fun run() {
        snake.move()
    }

    override val tactDuration: Long get() = 100

    override fun onRight() {
        snake.direction = Side.RIGHT
    }

    override fun onLeft() {
        snake.direction = Side.LEFT
    }

    override fun onUp() {
        snake.direction = Side.UP
    }

    override fun onDown() {
        snake.direction = Side.DOWN
    }
}