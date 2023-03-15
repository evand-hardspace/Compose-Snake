package common.models

data class Coordinate(val x: Int, val y: Int, val fieldSize: Int) {
    @JvmInline
    value class X(val value: Int)

    @JvmInline
    value class Y(val value: Int)

    val rightOrOpposite: Coordinate get() = if(x >= fieldSize - 1) copy(x = 0) else Coordinate(x + 1, y, fieldSize)
    val leftOrOpposite: Coordinate get() = if(x <= 0) copy(x = fieldSize - 1) else Coordinate(x - 1, y, fieldSize)
    val upOrOpposite: Coordinate get() = if(y <= 0) copy(y = fieldSize - 1) else Coordinate(x, y - 1, fieldSize)
    val downOrOpposite: Coordinate get() = if(y >= fieldSize - 1) copy(y = 0) else Coordinate(x, y + 1, fieldSize)

    val rightOrSame: Coordinate get() = if(x >= fieldSize - 1) copy() else Coordinate(x + 1, y, fieldSize)
    val leftOrSame: Coordinate get() = if(x <= 0) copy() else Coordinate(x - 1, y, fieldSize)
    val upOrSame: Coordinate get() = if(y <= 0) copy() else Coordinate(x, y - 1, fieldSize)
    val downOrSame: Coordinate get() = if(y >= fieldSize - 1) copy() else Coordinate(x, y + 1, fieldSize)
}

val Int.x: Coordinate.X get() = Coordinate.X(this)
val Int.y: Coordinate.Y get() = Coordinate.Y(this)

class CoordinateBuilder(val x: Int, val y: Int)

operator fun Coordinate.X.plus(y: Coordinate.Y): CoordinateBuilder = CoordinateBuilder(x = value, y = y.value)
operator fun CoordinateBuilder.plus(fieldSize: Int): Coordinate = Coordinate(x = x, y = y, fieldSize)

