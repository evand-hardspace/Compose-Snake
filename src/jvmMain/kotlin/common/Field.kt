package common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import common.models.CellType
import common.models.Coordinate
import common.models.Intent
import configs.globalFieldSize
import stdext.updatedAt

typealias Line = List<CellType>
typealias Matrix = List<Line>

class Field private constructor(initMatrix: Matrix) {

    private val _matrix = MutableStateFlow(initMatrix)
    val matrix: StateFlow<Matrix> = _matrix.asStateFlow()

    val size: Int = initMatrix.size

    fun update(coordinate: Coordinate, newType: CellType): Unit = _matrix.update {
        it.updatedAt(coordinate.x, coordinate.y) { newType }
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

    companion object {
        fun defaultField(size: Int = globalFieldSize) = Field(
            initMatrix = buildList {
                repeat(size) { line(size).run(::add) }
            }
        )

        private fun line(size: Int): Line = buildList {
            repeat(size) { add(CellType.EMPTY) }
        }
    }
}