package mc.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mc.model.Repository
import mc.ui.viewmodel.MainViewModel
import kotlin.math.ceil

@Composable
fun HorizontalSpacer(spacing: Int) {
    Spacer(modifier = Modifier.width(spacing.dp))
}

@Composable
fun VerticalSpacer(spacing: Int) {
    Spacer(modifier = Modifier.width(spacing.dp))
}

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
        lineHeight = (ceil(fontSize*1.3f).toInt()).sp,
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