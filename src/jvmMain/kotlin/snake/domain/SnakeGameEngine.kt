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

    override val tactDuration: Long get() = 300

    override fun onRight() {
        if (snake.headDirection != Side.LEFT) snake.headDirection = Side.RIGHT
    }

    override fun onLeft() {
        if (snake.headDirection != Side.RIGHT) snake.headDirection = Side.LEFT
    }

    override fun onUp() {
        if (snake.headDirection != Side.DOWN) snake.headDirection = Side.UP
    }

    override fun onDown() {
        if (snake.headDirection != Side.UP) snake.headDirection = Side.DOWN
    }
}