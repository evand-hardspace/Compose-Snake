package snake.domain

import common.FieldController
import snake.domain.models.CellType
import snake.domain.models.plus
import snake.domain.models.x
import snake.domain.models.y

class SnakeFieldController: FieldController() {

    var x = false
    override fun run() {
        x = !x
        field.update(coordinate = 0.x + 0.y, if(x) CellType.SNAKE else CellType.EMPTY)
    }

    override fun onRight() {

    }

    override fun onLeft() {

    }

    override fun onUp() {

    }

    override fun onDown() {

    }
}