package mc.model.animations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import mc.model.Sprite

class Star(
    override val size: Float,
    override val velocity: Pair<Float,Float>,
    position: Pair<Float, Float>
) : Sprite() {
    override var position = mutableStateOf(position)
    override val image: String = "drawable/star2.png"
    override val rotation: MutableState<Float> = mutableStateOf(0f)

    override fun move(delta: Float) {
        val dt = delta / 1000f
        val dx = velocity.first * dt /20f

        val newX = position.value.first - dx

        position.value = newX to position.value.second

        if (newX <= 0f) {
            position.value = 1f to position.value.second
        }
    }

}
