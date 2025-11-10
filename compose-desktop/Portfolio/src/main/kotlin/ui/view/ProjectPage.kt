@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package mc.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import mc.model.PictureOrientation
import mc.model.Project
import mc.model.Repository.buildText
import mc.model.projects
import mc.ui.components.AppText
import mc.ui.components.HorizontalSpacer
import mc.ui.viewmodel.MainViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProjectPage(project: Project = projects[0], vm: MainViewModel) {
    LaunchedEffect(true) {
        vm.initAnimations()
    }
    BoxWithConstraints{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val windowHeight = (vm.windowSize.height-12)
            val headerHeight = (windowHeight) * 0.1f
            val bodyHeight = windowHeight * 0.85f
            val detailsColumnWidth = vm.windowSize.width * 0.43f
            val picturesBoxWidth = vm.windowSize.width * 0.57f
            val actionBoxHeight = windowHeight * 0.05f
            ProjectHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight.dp)
                    .padding(vertical = 4.dp),
                project = project
            )
            ProjectBody(vm,  project,  detailsColumnWidth, picturesBoxWidth, bodyHeight)
            ActionBox(vm, project, actionBoxHeight)
        }
        /*Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${vm.windowSize.width} x ${vm.windowSize.height}",
            color = Color.White,
            textAlign = TextAlign.End
        )*/
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
                fontSize = 60,
                color = project.titleColor,
            )
            AppText(
                text = "Projet initié le ${project.creationDate.format(dateFormatter)}",
                fontSize = 42,
                color = project.titleColor,
                fontStyle = FontStyle.Italic,
            )

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProjectBody(
    vm: MainViewModel,
    project: Project,
    detailsColumnWidth: Float,
    picturesBoxWidth: Float,
    bodyHeight: Float
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().height(bodyHeight.dp).clipToBounds(),
    ) {
        val w = constraints.maxWidth.toFloat()
        val h = constraints.maxHeight.toFloat()
        vm.sprites.forEach { sprite ->
            val pos by sprite.position  // 👈 observe la valeur, déclenche recomposition
            Image(
                painter = painterResource(sprite.image),
                contentDescription = null,
                modifier = Modifier
                    .size(sprite.size.dp)
                    .absoluteOffset(
                        x = (pos.first * w).dp,
                        y = (pos.second * h).dp
                    ).rotate(sprite.rotation.value)
            )
        }
        val descriptionFontSize = when (project.description.length) {
            in (0..320) -> 36
            in (321..400) -> 32
            else -> 28
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            AppText(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                text = project.description,
                fontSize = descriptionFontSize,
                color = project.otherTextColor
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent)
                    .verticalScroll(rememberScrollState())
            ) {
                DetailsColumn(
                    modifier = Modifier.width(detailsColumnWidth.dp),
                    project = project
                )
                Box {
                    Row(
                        Modifier
                            .width(picturesBoxWidth.dp)
                            .height((bodyHeight*0.7f).dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PicturesBox(
                            project = project,
                            h = bodyHeight,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxHeight()
                            .width(80.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(project.backgroundColor, Color.Transparent)
                                )
                            )
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(80.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color.Transparent, project.backgroundColor)
                                )
                            )
                    )
                }
            }
        }

    }
}

@Composable
fun ActionBox(vm: MainViewModel, project: Project, height: Float) {
    Box(
        modifier = Modifier.fillMaxWidth().height(height.dp)
    ) {
        if (vm.currentProjectIndex.value > 0) {
            AppText(
                modifier = Modifier.align(Alignment.CenterStart).clickable {
                    vm.onPrevious()
                },
                text = "◀ Projet précédent",
                textDecoration = TextDecoration.Underline,
                fontSize = 20,
                color = project.otherTextColor,
            )
        }
        if (vm.currentProjectIndex.value < projects.size - 1) {
            AppText(
                modifier = Modifier.align(Alignment.CenterEnd).clickable {
                    vm.onNext()
                },
                text = "Projet suivant ▶",
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
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val titles = listOf("Technologies utilisées", "Fonctionalités clés", "Ce que j'ai appris / amélioré")
        val colors = listOf(project.titleColor, project.otherTextColor)
        val elements = listOf(project.linkedTechnologies, project.keyFunctionalities, project.whatILearn)
        val chips = listOf("⚙️", "🧩", "✨")
        HorizontalCategory(titles[0], colors, elements[0], chips[0])
        for (i in 1 until titles.size) {
            Category(title = titles[i], colors, elements[i], chips[i])
        }
        if (project.remarks.isNotEmpty()) {
            AppText(
                text = "Remarques : ${project.remarks}",
                color = project.otherTextColor,
                fontSize = 18
            )
        }
    }
}

@Composable
fun HorizontalCategory(title: String, colors: List<Color>, elements: List<String>, chip: String) {
    AppText(
        text = title,
        fontSize = 40,
        color = colors[0],
        textDecoration = TextDecoration.Underline,
    )
    val chunks = elements.chunked(3)
    chunks.forEach { chunk ->
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(chunk) { element ->
                AppText(
                    text = "$chip $element",
                    color = colors[1],
                    fontSize = 24,
                    modifier = Modifier
                        .border(1.dp, colors[0], RoundedCornerShape(25))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }

}

@Composable
fun Category(title: String, colors: List<Color>, elements: List<String>, chip: String) {
    AppText(
        text = title,
        fontSize = 40,
        color = colors[0],
        textDecoration = TextDecoration.Underline,
    )
    AppText(
        text = buildText(elements, chip),
        color = colors[1],
        fontSize = 24,
    )
}

@Composable
fun PicturesBox(project: Project, h: Float) {
    val portraitPictureModifier = Modifier
        .height(h.dp)
        .border(1.dp, project.otherTextColor)
    val landscapePictureModifier = Modifier
        .width(920.dp)
        .border(1.dp, project.otherTextColor)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(60.dp)
    ) {
        HorizontalSpacer(20)
        project.images.forEach { img ->
            Image(
                painter = painterResource(img.first),
                contentDescription = null,
                modifier = if (img.second == PictureOrientation.PORTRAIT) portraitPictureModifier else landscapePictureModifier,
                contentScale = if (img.second == PictureOrientation.LANDSCAPE) ContentScale.FillWidth else ContentScale.FillHeight,
            )
        }
        HorizontalSpacer(20)
    }
}
