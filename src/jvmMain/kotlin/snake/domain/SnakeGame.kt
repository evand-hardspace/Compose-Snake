package snake.domain

import common.BaseGame
import common.models.*

class SnakeGame : BaseGame() {

    private lateinit var snake: Snake

    override fun onCreate() {
        snake = Snake(
            field = field,
            onGameOver = ::restart
        )
    }

    override fun onRun() {
        snake.move()
    }

    override val tactDuration: Long get() = SNAKE_TACT_DURATION

    override fun onRight() {
        snake.turn(Side.RIGHT)
    }

    override fun onLeft() {
        snake.turn(Side.LEFT)
    }

    override fun onUp() {
        snake.turn(Side.UP)
    }

    override fun onDown() {
        snake.turn(Side.DOWN)
    }

    private companion object {
        const val SNAKE_TACT_DURATION = 100L
    }
}