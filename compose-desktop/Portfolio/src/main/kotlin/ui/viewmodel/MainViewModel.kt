package mc.ui.viewmodel

import Hexagon
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mc.model.FloatingAmongUs
import mc.model.Repository
import mc.model.Sprite
import mc.model.Star
import mc.model.projects
import mc.utils.TranslationManager
import java.util.Locale
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random
import mc.model.Repository.random

open class MainViewModel() {
    val currentLanguage = mutableStateOf("en")
    val toastMessage: MutableState<String> = mutableStateOf("")
    val showProjects = mutableStateOf(false)
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
    val fira = Font("font/firacode-regular.ttf")
    val playfair = Font("font/playfair-display.ttf")
    val currentProjectIndex = mutableStateOf(0)
    val currentPhotoDisplayedIndex = mutableStateOf(0)
    val sprites: SnapshotStateList<Sprite> = mutableStateListOf()
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

    init {
        TranslationManager.load(Locale.getDefault().language)
        currentLanguage.value = Locale.getDefault().language
    }
    fun initAnimations() {
        sprites.clear()
        when(currentProjectIndex.value) {
            0 -> {
                repeat(120) {
                    val size = (3..6).random().toFloat()

                    val velocity = Random.nextFloat()/2f + 0.025f
                    val pos = generateStarPosition()
                    sprites.add(Star(size, velocity to 0f, pos))
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
            }
            2 -> {
                repeat(45) {
                    val size = (35f..90f).random(0)
                    val x = Random.nextFloat() // normalisé
                    val y = 1.1f + Random.nextFloat() * 0.3f // spawn sous l'écran

                    sprites.add(Hexagon(size, x, y))
                }
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
        scope.launch {
            var lastTime = System.nanoTime()

            while (true) {
                val now = System.nanoTime()
                val delta = (now - lastTime) / 1_000_000f // ms
                lastTime = now

                sprites.forEach { sprite ->
                    sprite.move(delta)
                }

                delay(16)
            }
        }
    }

    fun onNext() {
        currentPhotoDisplayedIndex.value = 0
        sprites.clear()
        if (projects.size > currentProjectIndex.value + 1) {
            currentProjectIndex.value++
            updateFont()
        }
        initAnimations()
    }

    fun updateFont(){
        if(showProjects.value) {
            when(currentProjectIndex.value) {
                0 -> Repository.currentFont.value = orbitron
                1 -> Repository.currentFont.value = fredoka
                3 -> Repository.currentFont.value = playfair
                else -> Repository.currentFont.value = fira
            }
        }else{
            Repository.currentFont.value = fira
        }
    }

    fun onPrevious() {
        currentPhotoDisplayedIndex.value = 0
        sprites.clear()
        if (currentProjectIndex.value > 0) {
            currentProjectIndex.value--
            updateFont()
        }
        initAnimations()
    }

    fun selectProject(index: Int) {
        showProjects.value = true
        sprites.clear()
        currentProjectIndex.value = index
        println("select project with index: $index")
        initAnimations()
        updateFont()
    }

    fun showToast(message: String){
        toastMessage.value = message
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            toastMessage.value = ""
        }
    }
}