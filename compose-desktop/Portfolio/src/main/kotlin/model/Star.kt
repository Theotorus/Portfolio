package mc.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class Star(
    override val size: Float,
    override val velocity: Pair<Float,Float>,
    position: Pair<Float, Float>
) : Sprite() {
    override var position = mutableStateOf(position)
    override val image: String = "drawable/star2.png"
    override val rotation: MutableState<Float> = mutableStateOf(0f)

    override fun move() {
        position.value = (position.value.first-(velocity.first*10f)) to position.value.second
        if (position.value.first <= 0f) position.value = 1f to position.value.second
    }
}
