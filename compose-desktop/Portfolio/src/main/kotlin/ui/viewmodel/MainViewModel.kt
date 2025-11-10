package mc.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import mc.amongus.model.FloatingAmongUs
import mc.model.Repository
import mc.model.Sprite
import mc.model.Star
import mc.model.projects
import java.time.LocalTime
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random

open class MainViewModel() {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    var windowSize by mutableStateOf( IntSize(0, 0))
        private set

    fun updateWindowSize(width: Int, height: Int) {
        if (windowSize.width != width || windowSize.height != height) {
            windowSize = IntSize(width, height)
        }
    }

    val orbitron = Font("font/orbitron-regular.ttf")
    val fredoka = Font("font/fredoka.ttf")
    val currentProjectIndex = mutableStateOf(0)
    val sprites: SnapshotStateList<Sprite> = mutableStateListOf()
    val starProperties = listOf((5f to 0.00008f), (4f to 0.00004f), (2f to 0.00001f))
    val floatingAmongUsImage = listOf(
        "drawable/among_us_red_nb.png",
        "drawable/among_us_blue_nb.png",
        "drawable/among_us_cyan_nb.png",
        "drawable/among_us_gray_nb.png",
        "drawable/among_us_black_nb.png",
        "drawable/among_us_green_nb.png",
        "drawable/among_us_light_green_nb.png",
        "drawable/among_us_orange_nb.png",
        "drawable/among_us_violet_nb.png",
        "drawable/among_us_yellow_nb.png"
    )

    fun initAnimations() {
        sprites.clear()
        when(currentProjectIndex.value) {
            0 -> {
                repeat(200) {
                    val props = starProperties.random()
                    val pos = generateStarPosition()
                    sprites.add(Star(props.first, props.second to 0f, pos))
                }
            }
            1 -> {
                for(i in 0 until floatingAmongUsImage.size) {
                    val floatingAmongUsImage = floatingAmongUsImage[i]
                    sprites.add(
                        FloatingAmongUs(
                            id = i,
                            image = floatingAmongUsImage,
                            position = mutableStateOf(generateFloatingAmongUsPosition())
                        )
                    )
                }
                println(sprites.map{it.toString()})
            }
        }
        tick()
    }

    fun generateFloatingAmongUsPosition(): Pair<Float, Float>{
        val x = listOf(0f,0.8f).random()
        val y = gaussianClamped(mean = 0.5f, stdDev = 0.20f)
        return x to y
    }

    fun generateStarPosition(): Pair<Float, Float> {
        val x = Random.nextFloat() // uniforme horizontalement
        val y = gaussianClamped(mean = 0.5f, stdDev = 0.22f)
        return x to y
    }

    fun gaussianClamped(mean: Float, stdDev: Float): Float {
        // Box-Muller transform
        val u1 = Random.nextFloat().coerceAtLeast(1e-6f)
        val u2 = Random.nextFloat()
        val z = sqrt(-2f * ln(u1)) * cos(2f * Math.PI.toFloat() * u2)
        val value = mean + stdDev * z
        return value.coerceIn(0f, 1f)
    }

    fun tick() {
        scope.launch(Dispatchers.Default) {
            while (true) {
                delay(8)
                sprites.forEach { sprite ->
                    sprite.move()
                }
            }
        }
    }

    fun onNext() {
        sprites.clear()
        if (projects.size > currentProjectIndex.value + 1) {
            currentProjectIndex.value++
            when(currentProjectIndex.value) {
                0 -> Repository.currentFont.value = orbitron
                1 -> Repository.currentFont.value = fredoka
                else -> Repository.currentFont.value = fredoka
            }
        }
        initAnimations()
    }

    fun onPrevious() {
        sprites.clear()
        if (currentProjectIndex.value > 0) {
            currentProjectIndex.value--
            when(currentProjectIndex.value) {
                0 -> Repository.currentFont.value = orbitron
                1 -> Repository.currentFont.value = fredoka
                else -> Repository.currentFont.value = fredoka
            }
        }
        initAnimations()
    }
}