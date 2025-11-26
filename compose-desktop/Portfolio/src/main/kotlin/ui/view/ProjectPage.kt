@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package mc.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import mc.model.PictureOrientation
import mc.model.Project
import mc.model.animations.Confetti
import mc.model.animations.Hexagon
import mc.model.projects
import mc.ui.components.AppText
import mc.ui.components.MainHeader
import mc.ui.viewmodel.MainViewModel
import mc.utils.TranslationManager
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProjectPage(project: Project = projects[0], vm: MainViewModel) {
    LaunchedEffect(true) {
        vm.initAnimations()
    }
    Column{
        val windowHeight = (vm.windowSize.height - 12)
        val headerHeight = (windowHeight) * 0.1f
        val bodyHeight = windowHeight * 0.8f
        val detailsColumnWidth = vm.windowSize.width * 0.43f
        val actionBoxHeight = windowHeight * 0.05f
        MainHeader(vm, Modifier)
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            ProjectHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight.dp),
                project = project
            )
            ProjectBody(vm, project, detailsColumnWidth, bodyHeight)
            ProjectFooter(vm, project, actionBoxHeight)
        }
    }
    /*Text(
        modifier = Modifier.fillMaxWidth(),
        text = "${vm.windowSize.width} x ${vm.windowSize.height}",
        color = Color.White,
        textAlign = TextAlign.End
    )*/

}

@Composable
fun ProjectBackground(scope: BoxWithConstraintsScope, vm: MainViewModel) {
    val w = scope.constraints.maxWidth.toFloat()
    val h = scope.constraints.maxHeight.toFloat()
    for (sprite in vm.sprites) {
        val pos = sprite.position.value
        val rot = sprite.rotation.value
        if(vm.currentProjectIndex.value != 3){

            Image(
                painter = painterResource(sprite.image),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = pos.first * w
                        translationY = pos.second * h
                        rotationZ = rot
                    }
                    .size(sprite.size.dp)
                    .then(if (sprite !is Hexagon) Modifier.blur(0.6.dp) else Modifier.alpha(sprite.alpha))
            )
        }else{
            val confetti = sprite as Confetti
            val alpha = confetti.alpha.value

            val x = pos.first * w
            val y = pos.second * h

            Box(modifier = Modifier.size((confetti.size).dp).offset(x.dp,y.dp).rotate(confetti.rotation.value).background(color = confetti.color.second.copy(alpha = alpha)))
        }
    }


}

@Composable
fun ProjectHeader(modifier: Modifier, project: Project) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = project.title,
                fontSize = 54,
                color = project.titleColor,
            )
            val prefix: String = TranslationManager.get("projects.date_prefix")
            val date: String = project.creationDate.format(dateFormatter).toString()
            AppText(
                text = "$prefix $date",
                fontSize = 42,
                color = project.titleColor,
                fontStyle = FontStyle.Italic,
            )

        }
    }
}


@Composable
fun ProjectBody(
    vm: MainViewModel,
    project: Project,
    detailsColumnWidth: Float,
    bodyHeight: Float
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().height(bodyHeight.dp).clipToBounds(),
    ) {
        ProjectBackground(this, vm)
        Column(modifier = Modifier.fillMaxWidth()) {
            AppText(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                text = TranslationManager.get(project.description),
                fontSize = 28,
                color = project.otherTextColor
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent)
            ) {
                DetailsColumn(
                    modifier = Modifier.width(detailsColumnWidth.dp),
                    project = project
                )
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    PicturesBox(
                        project = project,
                        h = bodyHeight * 0.8f,
                        vm = vm
                    )
                }
            }
        }

    }
}

@Composable
fun ProjectFooter(vm: MainViewModel, project: Project, height: Float) {
    Box(
        modifier = Modifier.fillMaxWidth().height(height.dp)
    ) {
        if (vm.currentProjectIndex.value > 0) {
            val previous = TranslationManager.get("general.previous")
            AppText(
                modifier = Modifier.align(Alignment.CenterStart).clickable {
                    vm.onPrevious()
                },
                text = "◀ $previous",
                textDecoration = TextDecoration.Underline,
                fontSize = 20,
                color = project.otherTextColor,
            )
        }
        if (vm.currentProjectIndex.value < projects.size - 1) {
            val next = TranslationManager.get("general.next")
            AppText(
                modifier = Modifier.align(Alignment.CenterEnd).clickable {
                    vm.onNext()
                },
                text = "$next ▶",
                textDecoration = TextDecoration.Underline,
                fontSize = 20,
                color = project.otherTextColor,
            )
        }
    }
}

@Composable
fun DetailsColumn(modifier: Modifier, project: Project) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val titles = listOf(
            TranslationManager.get("projects.categories.0"),
            TranslationManager.get("projects.categories.1"),
            TranslationManager.get("projects.categories.2")
        )
        val colors = listOf(project.titleColor, project.otherTextColor)
        val elements = listOf(project.linkedTechnologies, project.keyFunctionalities, project.whatILearn)
        val chips = listOf("⚙️", "🧩", "✨")
        for (i in 0 until titles.size) {
            Category(title = titles[i], colors, elements[i], chips[i])
        }
        if (project.remarks.isNotEmpty()) {
            val t = TranslationManager.get("projects.categories.3")
            AppText(
                text = "$t : ${TranslationManager.get(project.remarks)}",
                color = project.otherTextColor,
                fontSize = 20
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Category(title: String, colors: List<Color>, elements: List<String>, chip: String) {
    AppText(
        text = title,
        fontSize = 40,
        color = colors[0],
        textDecoration = TextDecoration.Underline,
    )
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        elements.forEach { element ->
            val e = if (TranslationManager.get(element).contains("?")) element else TranslationManager.get(element)
            val text = "$chip $e"
            AppText(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .border(1.dp, colors[0], RoundedCornerShape(40))
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                text = text,
                color = colors[1],
                fontSize = 16,
            )
        }
    }
}

@Composable
fun PicturesBox(project: Project, h: Float, vm: MainViewModel) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentIndex = vm.currentPhotoDisplayedIndex.value

        Crossfade(
            targetState = currentIndex,
            animationSpec = tween(durationMillis = 800)
        ) { index ->

            val img = project.images[index]

            Image(
                painter = painterResource(img.first),
                contentDescription = null,
                modifier = Modifier.height(h.dp).clip(RoundedCornerShape(if(img.second == PictureOrientation.LANDSCAPE)10 else 2)),
                contentScale = if (img.second == PictureOrientation.LANDSCAPE)
                    ContentScale.FillWidth
                else
                    ContentScale.FillHeight
            )
        }

        if(project.images.size > 1) PictureSelector(vm, project)
    }
}

@Composable
fun PictureSelector(vm: MainViewModel, project: Project) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        for (i in 0 until project.images.size) {
            CirclePictureSelectorButton(vm, i)
        }
    }
}

@Composable
fun CirclePictureSelectorButton(vm: MainViewModel, index: Int) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .clickable {
                vm.currentPhotoDisplayedIndex.value = index
            }
            .border(2.dp, Color.White, CircleShape)
            .then(
                if (vm.currentPhotoDisplayedIndex.value == index) Modifier.background(
                    Color.White,
                    CircleShape
                ) else Modifier
            ),

        )
}