package common

import common.models.*

class RestartAnimator(
    private val field: Field,
    private val restartAction: () -> Unit,
) {

    var isRunning: Boolean = false
        private set

    private var gameOverAnimCounter = 0

    fun onRestart() {
        isRunning = true
    }

    fun onRun() {
        if (gameOverAnimCounter < ANIMATION_ITERATIONS_COUNT) {
            gameOverAnimCounter++
            gameOverAnimation().run(field::update)
        } else {
            restartAction()
            gameOverAnimCounter = 0
            isRunning = false
        }
    }

    private fun gameOverAnimation(): List<Intent> = field.let { field ->
        field.matrix.value.mapIndexed { outIndex, line ->
            line.mapIndexed { innerIndex, cellType ->
                innerIndex.x + outIndex.y + field.size changeTo when (cellType) {
                    CellType.MAIN -> CellType.EMPTY
                    CellType.EMPTY -> CellType.MAIN
                    CellType.SECONDARY -> CellType.SECONDARY
                }
            }
        }.flatten()
    }

    private companion object {
        const val ANIMATION_ITERATIONS_COUNT = 5
    }
}