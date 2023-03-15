package common

import configs.GameType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import snake.domain.SnakeFieldController
import common.models.Side

abstract class FieldController(
    val field: Field = Field.defaultField(),
    private val defaultDuration: Long = 1_000L,
) {
    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        scope.launch {
            gameCycle(tactDuration)
        }
    }

    protected open val tactDuration: Long get() = defaultDuration

    protected abstract fun run()

    protected abstract fun onRight()

    protected abstract fun onLeft()

    protected abstract fun onUp()

    protected abstract fun onDown()

    private suspend fun gameCycle(tactDuration: Long): Nothing {
        while (true) {
            delay(tactDuration)
            run()
        }
    }

    fun click(side: Side): Unit = when (side) {
        Side.RIGHT -> onRight()
        Side.LEFT -> onLeft()
        Side.UP -> onUp()
        Side.DOWN -> onDown()
    }

    companion object {
        fun provide(gameType: GameType): FieldController = when(gameType) {
            GameType.SNAKE -> SnakeFieldController()
        }
    }
}