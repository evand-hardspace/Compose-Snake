package common

import common.models.*
import configs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import snake.domain.SnakeGameEngine

abstract class GameEngine(
    val field: Field = Field.defaultField(),
    private val defaultDuration: Long = globalDefaultTactDuration,
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private var intendsToRestart: Boolean = false

    private var gameOverAnimCounter = 0

    fun start() {
        onCreate()
        scope.launch { gameCycle(tactDuration) }
    }

    protected open val tactDuration: Long get() = defaultDuration

    protected abstract fun onCreate()

    private fun onRestart() {
        field.clear()
        onCreate()
    }

    fun restart() {
        intendsToRestart = true
    }

    protected abstract fun run()

    protected abstract fun onRight()

    protected abstract fun onLeft()

    protected abstract fun onUp()

    protected abstract fun onDown()

    //todo refactor game restart animation logic, looks awful
    private suspend fun gameCycle(tactDuration: Long): Nothing {
        while (true) {
            if(intendsToRestart) {
                if (gameOverAnimCounter < 5) {
                    gameOverAnimCounter++
                    gameOverAnimation().run(field::update)
                } else {
                    onRestart()
                    gameOverAnimCounter = 0
                    intendsToRestart = false
                }
            }
            delay(tactDuration)
            if(intendsToRestart.not()) run()
        }
    }

    fun click(side: Side): Unit = when (side) {
        Side.RIGHT -> onRight()
        Side.LEFT -> onLeft()
        Side.UP -> onUp()
        Side.DOWN -> onDown()
    }

    private fun gameOverAnimation(): List<Intent> = field.matrix.value
        .mapIndexed { outIndex, line ->
            line.mapIndexed { innerIndex, cellType ->
                innerIndex.x + outIndex.y + field.size changeTo when (cellType) {
                    CellType.MAIN -> CellType.EMPTY
                    CellType.EMPTY -> CellType.MAIN
                    CellType.SECONDARY -> CellType.SECONDARY
                }
            }
        }.flatten()

    companion object {
        fun provide(gameType: GameType): GameEngine = when(gameType) {
            GameType.SNAKE -> SnakeGameEngine()
        }
    }
}