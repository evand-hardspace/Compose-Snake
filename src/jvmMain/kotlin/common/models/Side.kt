package common.models

enum class Side {
    RIGHT, LEFT, UP, DOWN;

    val opposite: Side
        get() = when (this) {
            RIGHT -> LEFT
            LEFT -> RIGHT
            UP -> DOWN
            DOWN -> UP
        }
}