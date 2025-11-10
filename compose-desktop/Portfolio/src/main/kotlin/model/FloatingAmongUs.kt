package mc.amongus.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import mc.model.Sprite
import kotlin.math.cos
import kotlin.math.sin

class FloatingAmongUs(
    val id: Int,
    override val image: String,
    override var position: MutableState<Pair<Float, Float>>
) : Sprite() {

    val xMin = -0.5f
    val yMin = -0.5f
    val xMax = 1.5f
    val yMax = 1.5f
    private val minSize = 80
    private val maxSize = 150
    val dx: Float = (2..8).random()/10000f
    val dy: Float = (4..6).random() / 10000f

    private var timer = (200..2000).random()
    private val turnLeft = (0..9).random() < 5
    private val angleDirection : Int = if(position.value.first == -0.4f)((0..45).toList() +(315..359).toList()).random() else(135..225).random()
    override val rotation = mutableFloatStateOf(0f)
    override val velocity: Pair<Float, Float> = ( (5..25).random()/100f to (1..10).random()/100f )
    override val size = (minSize..maxSize).random() / 1f


    override fun move() {
        val posX = position.value.first + (cos(angleDirection * (Math.PI.toFloat() / 180f)) * dx)
        val posY = position.value.second + (sin(angleDirection * (Math.PI.toFloat() / 180f)) * dy)
        if (posX < xMin || posY < yMin || posX > xMax || posY > yMax) {
            recall()
        }else{
            position.value = posX to posY
        }
        if (turnLeft) {
            rotation.floatValue += 0.5f
        } else {
            rotation.floatValue -= 0.5f
        }
    }

    private fun recall() {
        val x = if (angleDirection in (135..225)) {
            xMax
        } else {
            xMin
        }
        val y = if(position.value.first < 1f) yMin else if(position.value.second < 0f) yMin else position.value.second
        position.value = x to y
        timer = (500..2000).random()
    }

    override fun toString(): String {
        return "FloatingAmongUs(id=$id, image=$image, timer=$timer,  rotation=$rotation, turnLeft=$turnLeft, size=$size, dx=$dx)"
    }


}