package mc.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.platform.Font
import kotlin.math.pow
import kotlin.random.Random

object Repository {
    var currentFont = mutableStateOf(Font("font/firacode-regular.ttf"))
    fun ClosedFloatingPointRange<Float>.random(precision: Int): Float{
        require(precision >= 0) { "precision must be >= 0" }
        val k = 10f.pow(precision)
        val n1 = (start * k).toInt()
        val n2 = (endInclusive * k).toInt()
        val r = Random.nextInt(n1,n2+1)
        println("($n1..$n2).random($precision) = ${r/k} ")
        return (r / k)
    }
}