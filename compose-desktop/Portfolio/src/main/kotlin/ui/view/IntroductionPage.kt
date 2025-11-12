package mc.ui.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mc.ui.components.AppText
import mc.ui.viewmodel.MainViewModel
import mc.utils.TranslationManager
import java.awt.Desktop
import java.net.URI

@Composable
fun IntroductionPage(vm: MainViewModel) {
    Box {
        Background(vm.windowSize.width)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.background(color = Color.Black.copy(alpha = 0.25f))) {
                AppText(
                    text = "Christopher Melis",
                    color = Color.White,
                    fontSize = 44
                )
                AppText(
                    text = TranslationManager.get("intro.title"),
                    color = Color.White,
                    fontSize = 40,
                )
            }
            AppText(
                modifier = Modifier.background(color = Color.Black.copy(alpha = 0.25f)),
                text = TranslationManager.get("intro.welcome"),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 24
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppText(
                    text = TranslationManager.get("intro.my_projects"),
                    color = Color.White,
                    fontSize = 40,
                    textDecoration = TextDecoration.Underline,
                )
                Column {
                    HoverButton(
                        modifier = Modifier,
                        text = "> Cosmic",
                        fontSize = 36,
                        font = vm.fira
                    ) {
                        vm.selectProject(0)
                    }

                    HoverButton(
                        modifier = Modifier,
                        text = "> In life Among Us",
                        fontSize = 36,
                        font = vm.fira,
                    ) {
                        vm.selectProject(1)
                    }
                    HoverButton(
                        modifier = Modifier,
                        text = "> Cobria",
                        fontSize = 36,
                        font = vm.fira,
                    ) {
                        vm.selectProject(2)
                    }
                }
            }
        }
        Footer(
            vm = vm,
            modifier = Modifier.fillMaxWidth().background(Color.Black.copy(alpha = 0.5f)).align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                modifier = Modifier.clickable {
                    TranslationManager.load("en")
                    vm.currentLanguage.value = "en"
                },
                text = "EN",
                color = if(vm.currentLanguage.value == "en") Color.White else Color.Gray
            )
            Text(
                modifier = Modifier.clickable {
                    TranslationManager.load("fr")
                    vm.currentLanguage.value = "fr"
                },
                text = "FR",
                color = if(vm.currentLanguage.value == "fr") Color.White else Color.Gray
            )
            Text(
                modifier = Modifier.clickable {
                    TranslationManager.load("nl")
                    vm.currentLanguage.value = "nl"
                },
                text = "NL",
                color = if(vm.currentLanguage.value == "nl") Color.White else Color.Gray
            )
        }
    }
}

@Composable
private fun Background(w: Int){
    val infiniteTransition = rememberInfiniteTransition()
    val offsetAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5_000, 0, EaseInOutQuart),
            repeatMode = RepeatMode.Reverse
        )
    )
    val offsetAnim2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5_000, 0, EaseInOutQuart),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Création du gradient animé
    val gradientBrush = Brush.radialGradient(
        colors = listOf(
            Color.Transparent,
            Color(0xFFE03535),
            //Color.Transparent,
            Color(0xFFC9B21C),
            Color(0xFFC9B21C),
            //Color.Transparent,
            Color(0xFF25BA1A),
            Color(0xFF25BA1A),
            Color(0xFF25BA1A),
            //Color.Transparent,
            Color(0xFF1A3DBA),
            Color(0xFF1A3DBA),
            Color(0xFF1A3DBA),
            Color(0xFF1A3DBA),
            //Color.Transparent,
            Color(0xFF571ABA),
            Color(0xFF571ABA),
            Color(0xFF571ABA),
            Color(0xFF571ABA),
            Color.Transparent,
        ),
        center = Offset(x = (500f * offsetAnim) - 750f, y = (400f * offsetAnim)),
        radius = w * 5 * offsetAnim,
        tileMode = TileMode.Clamp,
    )
    val gradientBrush2 = Brush.radialGradient(
        colors = listOf(
            Color.Transparent,
            Color(0xFFE03535),
            //Color.Transparent,
            Color(0xFFC9B21C),
            //Color.Transparent,
            Color(0xFF25BA1A),
            //Color.Transparent,
            Color(0xFF1A3DBA),
            Color(0xFF1A3DBA),
            //Color.Transparent,
            Color(0xFF571ABA),
            Color(0xFF571ABA),
            Color.Transparent,
        ),
        center = Offset(x = w * 1.5f, y = Float.POSITIVE_INFINITY),
        radius = w * 5 * offsetAnim2,
        tileMode = TileMode.Clamp,
    )
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
    )
    Box(
        modifier = Modifier.fillMaxSize().background(brush = gradientBrush),
    )
    Box(
        modifier = Modifier.fillMaxSize().background(brush = gradientBrush2),
    )
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource("drawable/hexagons3.png"), // ton motif transparent
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
private fun Footer(vm: MainViewModel, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        val clipboardManager = LocalClipboardManager.current
        HoverText(
            text = "christophermx95@gmail.com",
            fontSize = 22,
            color = Color.White
        ){
            clipboardManager.setText(AnnotatedString("christophermx95@gmail.com"))
            vm.showToast(TranslationManager.get("general.copied_to_clipboard"))
        }
        HoverText(
            text = "https://github.com/Theotorus",
            fontSize = 22,
            color = Color.White,
        ) {
            Desktop.getDesktop().browse(URI("https://github.com/Theotorus"))
        }

    }
}

@Composable
fun HoverButton(modifier: Modifier, text: String, fontSize: Int, font: Font, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Button(
        onClick = { onClick() },
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isHovered) Color(255, 255, 255, 128) else Color.Transparent
        ),
        modifier = modifier.padding(8.dp).graphicsLayer {
            scaleX = if (isHovered) 1.05f else 1f
            scaleY = if (isHovered) 1.05f else 1f
        }
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = FontFamily(font),
            color = if (isHovered) Color(23, 18, 18, 255) else Color.White
        )
    }
}

@Composable
fun HoverText(text: String, fontSize: Int, color: Color, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    AppText(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
        ) {
            try {
                onClick()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
        text = text,
        color = if (isHovered) Color(248, 45, 45, 255) else color,
        fontSize = fontSize
    )
}
