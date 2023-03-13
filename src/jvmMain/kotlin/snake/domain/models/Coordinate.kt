package snake.domain.models

data class Coordinate(val x: Int, val y: Int) {
    @JvmInline
    value class X(val value: Int)

    @JvmInline
    value class Y(val value: Int)
}

val Int.x: Coordinate.X get() = Coordinate.X(this)
val Int.y: Coordinate.Y get() = Coordinate.Y(this)

operator fun Coordinate.X.plus(y: Coordinate.Y): Coordinate = Coordinate(x = value, y = y.value)
operator fun Coordinate.Y.plus(x: Coordinate.X): Coordinate = Coordinate(x = x.value, y = value)