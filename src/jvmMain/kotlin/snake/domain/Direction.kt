package snake.domain

import common.models.Side

class Direction(initSide: Side) {

    var direction: Side = initSide
        private set

    var intendDirection: Side? = null

    fun actualize(): Unit = intendDirection?.let {
        direction = it
        intendDirection = null
    } ?: Unit
}