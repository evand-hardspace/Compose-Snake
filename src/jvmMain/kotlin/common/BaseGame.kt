package common

import common.models.*
import configs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import stdext.suspendLoop

abstract class BaseGame(
    override val field: Field = Field.defaultField(),
    private val defaultDuration: Long = globalDefaultTactDuration,
): Game {

    private val scope = CoroutineScope(Dispatchers.Default)
    private val restartAnimator = RestartAnimator(::onRestart) { field }

    protected open val tactDuration: Long get() = defaultDuration

    override fun start() {
        onCreate()
        scope.launch { gameCycle(tactDuration) }
    }

    override fun restart() {
        restartAnimator.onRestart()
    }

    override fun click(side: Side): Unit = when (side) {
        Side.RIGHT -> onRight()
        Side.LEFT -> onLeft()
        Side.UP -> onUp()
        Side.DOWN -> onDown()
    }
    protected abstract fun onCreate()

    protected abstract fun onRun()

    protected abstract fun onRight()

    protected abstract fun onLeft()

    protected abstract fun onUp()

    protected abstract fun onDown()

    private suspend fun gameCycle(tactDuration: Long): Nothing =
        suspendLoop(tactDuration) { run() }

    private fun run() {
        if (restartAnimator.isRunning) restartAnimator.onRun() else onRun()
    }

    private fun onRestart() {
        field.clear()
        onCreate()
    }
}