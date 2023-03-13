package snake.domain

import common.FieldController
import snake.domain.models.*

class SnakeFieldController : FieldController() {

    var coordinate = 0.x + 0.y
    override fun run() {
        field.update(coordinate = coordinate, CellType.SNAKE)
    }

    override val tactDuration: Long get() = 100

    override fun onRight() {
        coordinate = coordinate.copy(x = coordinate.x + 1)
    }

    override fun onLeft() {
        coordinate = coordinate.copy(x = coordinate.x - 1)
    }

    override fun onUp() {
        coordinate = coordinate.copy(y = coordinate.y - 1)
    }

    override fun onDown() {
        coordinate = coordinate.copy(y = coordinate.y + 1)
    }
}