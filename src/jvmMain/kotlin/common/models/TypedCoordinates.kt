package common.models

data class TypedCoordinates(
    val coordinate: Coordinate,
    val type: CellType
)

operator fun Coordinate.plus(type: CellType): TypedCoordinates = TypedCoordinates(this, type)