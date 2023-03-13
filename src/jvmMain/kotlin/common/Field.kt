package common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import snake.configs.fieldSize
import snake.domain.models.CellType
import snake.domain.models.Coordinate
import stdext.updatedAt

typealias Line = List<CellType>
typealias Matrix = List<Line>

class Field(initMatrix: Matrix) {

    private val _matrix = MutableStateFlow(initMatrix)
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

    fun update(coordinate: Coordinate, newType: CellType): Unit = _matrix.update {
        it.updatedAt(coordinate.x, coordinate.y) { newType }
    }

    fun update(coordinates: List<Coordinate>, newType: CellType) {
        var value = _matrix.value
        coordinates.forEach { coordinate ->
            value = value.updatedAt(coordinate.x, coordinate.y) { newType }
        }
        _matrix.value = value
    }

    companion object {
        fun defaultField(size: Int = fieldSize) = Field(
            initMatrix = buildList {
                repeat(size) { line(size).run(::add) }
            }
        )

        private fun line(size: Int): Line = buildList {
            repeat(size) { add(CellType.EMPTY) }
        }
    }
}