@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package mc

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import mc.model.projects
import mc.ui.view.ProjectPage
import mc.ui.viewmodel.MainViewModel
import java.awt.Dimension
import kotlin.math.round
import kotlin.math.roundToInt

fun main() = application {
    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(onCloseRequest = ::exitApplication, state = state) {
        window.minimumSize = Dimension(
            (1200f * LocalDensity.current.density).roundToInt(),
            (600f*LocalDensity.current.density).roundToInt()
        )
        App()
    }
}

@Composable
fun App() {
    val vm = MainViewModel()
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.medium,
            color = projects[vm.currentProjectIndex.value].backgroundColor
        ) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val w = (constraints.maxWidth / LocalDensity.current.density).toInt()
                val h = (constraints.maxHeight / LocalDensity.current.density).toInt()
                // Quand w/h changent, on écrit dans le ViewModel (déclenche recomposition partout où il est observé)

                LaunchedEffect(w, h) {
                    vm.updateWindowSize(w, h)
                }
                ProjectPage(project = projects[vm.currentProjectIndex.value], vm = vm)
            }
        }
    }
}