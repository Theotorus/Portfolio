package mc.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import mc.model.Repository
import mc.ui.viewmodel.MainViewModel
import mc.utils.TranslationManager
import kotlin.math.ceil

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    fontSize: Int,
    fontStyle: FontStyle = FontStyle.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontFamily = FontFamily(Repository.currentFont.value),
        fontSize = fontSize.sp,
        fontStyle = fontStyle,
        textDecoration = textDecoration,
        lineHeight = (ceil(fontSize * 1.3f).toInt()).sp,
    )
}

@Composable
fun ToastHost(vm: MainViewModel) {
    val toastMessage by vm.toastMessage

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        AnimatedVisibility(
            visible = toastMessage.isNotEmpty(),
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            if (toastMessage.isNotEmpty()) {
                Box(
                    Modifier
                        .padding(bottom = 40.dp)
                        .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = toastMessage,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun MainHeader(vm: MainViewModel, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().background(Color.Black).padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (!vm.showProjects.value) {
            Box {}
        } else {
            IconButton(
                modifier = Modifier.size(28.dp),
                onClick = {
                    vm.showProjects.value = false
                    vm.sprites.clear()
                }
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home button", tint = Color.White)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val languages = listOf("en", "fr", "nl")
            for (lang in languages) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp).clickable {
                        onLanguageButtonClick(vm, lang)
                    },
                    text = lang.uppercase(),
                    color = if (vm.currentLanguage.value == lang) Color.White else Color.Gray,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

private fun onLanguageButtonClick(vm: MainViewModel, language: String) {
    TranslationManager.load(language)
    vm.currentLanguage.value = language
}