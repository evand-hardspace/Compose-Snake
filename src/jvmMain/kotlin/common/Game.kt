package common

import common.models.Side

interface Game {
    val field: Field
    fun start()
    fun restart()
    fun click(side: Side)
}