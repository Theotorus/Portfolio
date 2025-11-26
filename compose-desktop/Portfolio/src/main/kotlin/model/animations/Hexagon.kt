package mc.model.animations

import androidx.compose.runtime.mutableStateOf
import mc.model.Sprite
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

class Hexagon(
    override val size: Float,
    startX: Float,
    startY: Float
) : Sprite() {

    override var position = mutableStateOf(startX to startY)
    override val image: String = "drawable/hexa.png"
    override val rotation = mutableStateOf(0f)
    override val velocity: Pair<Float, Float> = 0f to 0f
    val alpha = rand(0.45f, 0.75f)

    private val speed = rand(0.02f, 0.07f)
    private val wobbleSpeed = rand(0.3f, 0.8f)
    private val wobbleAmplitude = rand(0.01f, 0.03f)
    private var wobblePhase = rand(0f, (2f * PI).toFloat())

    private val rotationSpeed = rand(-20f, 20f)

    private fun rand(min: Float, max: Float) =
        Random.nextFloat() * (max - min) + min

    override fun move(deltaMs: Float) {
        val dt = deltaMs / 1000f

        var (x, y) = position.value

        y -= speed * dt

        wobblePhase += wobbleSpeed * dt
        x += sin(wobblePhase) * wobbleAmplitude * dt

        rotation.value = (rotation.value + rotationSpeed * dt) % 360f

        if (y < -0.2f) {
            y = 1f + Random.nextFloat() * 0.3f
            x = Random.nextFloat()
        }

        position.value = x to y
    }
}
