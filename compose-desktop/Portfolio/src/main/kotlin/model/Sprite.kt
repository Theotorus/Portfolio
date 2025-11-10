package mc.model

import androidx.compose.runtime.MutableState

abstract class Sprite{
    abstract val size: Float
    abstract var position: MutableState<Pair<Float, Float>>
    abstract val velocity: Pair<Float,Float>
    abstract val image: String
    abstract val rotation: MutableState<Float>

    abstract fun move()
}