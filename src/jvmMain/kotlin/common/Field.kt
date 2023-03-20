package common

import common.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import configs.globalFieldSize
import stdext.updatedAt

typealias Line = List<CellType>
typealias Matrix = List<Line>
typealias CoordinateLine = List<Coordinate>
typealias CoordinateMatrix = List<CoordinateLine>

class Field private constructor(initMatrix: Matrix) {

    private val _matrix = MutableStateFlow(initMatrix)
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

    val size: Int = initMatrix.size

    val coordinateMatrix: CoordinateMatrix
        get() = matrix.value.let { coordMatrix ->
            coordMatrix.mapIndexed { y, line ->
                List(line.size) { x ->
                    x.x + y.y + coordMatrix.size
                }
            }
        }

    fun update(intent: Intent): Unit = _matrix.update {
        it.updatedAt(intent.coordinate.x, intent.coordinate.y) { intent.newCellType }
    }

    fun update(intents: List<Intent?>) {
        var mutableValue = _matrix.value
        intents
            .filterNotNull()
            .forEach { intent ->
                mutableValue = mutableValue.updatedAt(intent.coordinate.x, intent.coordinate.y) { intent.newCellType }
            }
        _matrix.value = mutableValue
    }

    fun getCellType(coordinate: Coordinate): CellType = _matrix.value[coordinate.y][coordinate.x]

    fun clear() {
        _matrix.update { emptyMatrix(size) }
    }

    companion object {
        fun defaultField(size: Int = globalFieldSize) = Field(
            initMatrix = emptyMatrix(size)
        )

        private fun emptyMatrix(size: Int): Matrix = buildList {
            repeat(size) { emptyLine(size).run(::add) }
        }

        private fun emptyLine(size: Int): Line = buildList {
            repeat(size) { add(CellType.EMPTY) }
        }
    }
}