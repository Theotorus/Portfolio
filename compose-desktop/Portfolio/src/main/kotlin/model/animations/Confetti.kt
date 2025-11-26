package mc.model.animations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import mc.model.Sprite
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Confetti(
    override var position: MutableState<Pair<Float, Float>>,
    override val rotation: MutableState<Float>,
) : Sprite() {

    override val size: Float = 20f
    val color = listOf(
        "red" to Color(1f, 0.6f, 0.6f),
        "yellow" to Color(1f, 0.94f, 0.6f),
        "blue" to Color(0.6f, 0.7f, 1f),
        "green" to Color(0.7f, 1f, 0.7f),
        "pink" to Color(1f, 0.7f, 0.9f),
        "white" to Color(0.95f, 0.95f, 0.95f)
    ).random()
    override val velocity: Pair<Float, Float> = (0.25f*cos(rotation.value)) to (0.25f*sin(rotation.value))
    override val image: String = ""
    val alpha = mutableStateOf(1f)
    private var rotationSpeed = (Random.nextFloat() - 0.5f) * 180f // deg/sec

    private var life = 0f         // en ms
    private val maxLife = 1600f + Random.nextFloat() * 800f

    var isDead: Boolean = false
        private set


    override fun move(delta: Float) {
        if (isDead) return

        val dt = delta / 1000f // ms -> s

        life += delta
        val t = (life / maxLife).coerceIn(0f, 1f)
        val eased = t * t
        alpha.value = 1f - eased           // slow then fast easing

        if (t >= 1f) {
            isDead = true
            return
        }

        var (x, y) = position.value
        x += velocity.first * dt
        y += velocity.second * dt
        rotation.value += rotationSpeed * dt

        position.value = x to y
    }
}
