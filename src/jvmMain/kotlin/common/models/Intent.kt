package common.models

data class Intent(val coordinate: Coordinate, val newCellType: CellType)

infix fun Coordinate.changeTo(newType: CellType): Intent = Intent(this, newType)