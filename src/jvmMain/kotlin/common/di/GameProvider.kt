package common.di

import common.Game
import configs.GameType
import configs.globalGameType
import snake.domain.SnakeGame

object GameProvider {
    fun provide(): Game = when (globalGameType) {
        GameType.SNAKE -> SnakeGame()
    }
}