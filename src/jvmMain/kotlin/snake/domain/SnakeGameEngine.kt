package snake.domain

import common.GameEngine
import common.models.*

class SnakeGameEngine : GameEngine() {

    private lateinit var foodGenerator: FoodGenerator

    private lateinit var snake: Snake

    override fun onCreate() {
        foodGenerator = FoodGenerator(field = field)
        snake = Snake(
            field = field,
            onAteFood = foodGenerator::generateFood,
            onGameOver = ::restart
        )
    }

    override fun run() {
        snake.move()
    }

    override val tactDuration: Long get() = 100

    override fun onRight() {
        if (snake.head.direction != Side.LEFT) snake.head.intendDirection = Side.RIGHT
    }

    override fun onLeft() {
        if (snake.head.direction != Side.RIGHT) snake.head.intendDirection = Side.LEFT
    }

    override fun onUp() {
        if (snake.head.direction != Side.DOWN) snake.head.intendDirection = Side.UP
    }

    override fun onDown() {
        if (snake.head.direction != Side.UP) snake.head.intendDirection = Side.DOWN
    }
}